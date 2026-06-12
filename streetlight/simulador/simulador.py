"""
Simulador de postes de iluminacao publica de Braga.

Cada poste reporta:
  - luminosidade (luz ambiente 0-100): e' o que decide o brilho
  - potencia reportada: serve so' para o backend DETETAR AVARIA
    (se reporta 0 mas devia estar aceso, e' avaria)

NOTA: a potencia REAL (consumo) e' calculada pelo backend a partir do brilho
decidido pelo motor (100%=80W, 50%=40W, 0%=0W). Por isso os graficos tem
variedade: ao entardecer os postes ficam a 50% (40W), de noite a 100% (80W),
de dia a 0% (0W).

Um poste avariado mantem-se em falha ate' ser resolvido no painel.

Como correr (terminal, dentro da pasta 'simulador'):
    pip install requests
    python simulador.py
"""
import requests
import random
import time

URL_LEITURAS = "http://localhost:8080/api/leituras"
URL_POSTES = "http://localhost:8080/api/postes"

INTERVALO = 5            # segundos entre rondas
PAUSA_ENTRE_POSTES = 0.3
HORAS_POR_RONDA = 1
PROB_AVARIA = 0.03       # 3% por poste (so' avaria se nao estiver ja' avariado)
N_POSTES = 12


def luz_ambiente(hora):
    if 7 <= hora <= 18:
        return random.randint(70, 100)   # dia: claro -> postes apagam (0%)
    if hora in (6, 19):
        return random.randint(30, 60)    # entardecer -> postes a 50%
    return random.randint(0, 20)         # noite: escuro -> postes a 100%


def estados_atuais():
    try:
        postes = requests.get(URL_POSTES, timeout=5).json()
        return {p["id"]: p["estado"] for p in postes}
    except requests.exceptions.RequestException:
        return {}


def main():
    hora = 18
    print("Simulador de Braga a arrancar. Ctrl+C para parar.")
    print(f"Ritmo: {INTERVALO}s por ronda. A potencia real e' calculada pelo backend (proporcional ao brilho).\n")
    while True:
        hora = (hora + HORAS_POR_RONDA) % 24
        momento = "DIA" if 7 <= hora <= 18 else ("ENTARDECER" if hora in (6, 19) else "NOITE")
        print(f"--- Hora simulada: {hora}h ({momento}) ---")
        estados = estados_atuais()
        for poste_id in range(1, N_POSTES + 1):
            lum = luz_ambiente(hora)
            em_falha = estados.get(poste_id) == "FALHA"

            if em_falha:
                pot = 0
                nota = "  (em FALHA - aguarda reparacao)"
            elif lum < 70 and random.random() < PROB_AVARIA:
                pot = 0          # devia acender mas reporta 0 -> backend deteta avaria
                nota = "  <-- NOVA AVARIA"
            else:
                # reporta que esta' a funcionar (valor simbolico; o consumo real
                # e' calculado pelo backend a partir do brilho)
                pot = 1 if lum < 70 else 0
                nota = ""

            leitura = {"posteId": poste_id, "luminosidade": lum, "potencia": pot, "brilho": 0, "horaSimulada": hora}
            try:
                requests.post(URL_LEITURAS, json=leitura, timeout=5)
                print(f"  Poste {poste_id:2}: luz={lum:3} -> enviado{nota}")
            except requests.exceptions.RequestException:
                print(f"  Poste {poste_id:2}: backend nao responde (o Java esta' a correr?)")
            time.sleep(PAUSA_ENTRE_POSTES)
        print(f"(pausa de {INTERVALO}s)\n")
        time.sleep(INTERVALO)


if __name__ == "__main__":
    main()
