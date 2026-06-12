# Simulador de postes (Python)

Simula os postes inteligentes a enviar leituras (luz e potência) para o backend.

## Pré-requisitos
- Python instalado
- Backend a correr em http://localhost:8080

## Como correr
```bash
pip install requests
python simulador.py
```

## Configuração (topo do simulador.py)
- `INTERVALO` — segundos entre rondas (por defeito 5)
- `PROB_AVARIA` — probabilidade de avaria por poste (por defeito 0.03)

Um poste avariado mantém-se em falha até ser resolvido no painel da aplicação.
