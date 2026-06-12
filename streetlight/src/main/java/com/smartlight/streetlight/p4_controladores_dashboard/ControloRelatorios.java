package com.smartlight.streetlight.p4_controladores_dashboard;

import com.smartlight.streetlight.p2_controladores_dados.ControloGestaoPostes;
import com.smartlight.streetlight.p5_controladores_administracao.ControloConfiguracao;
import com.smartlight.streetlight.p6_dados_monitorizacao.Leitura;
import com.smartlight.streetlight.p6_dados_monitorizacao.LeituraRepository;
import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p8_dados_dashboard.*;
import com.smartlight.streetlight.p9_dados_administracao.Configuracao;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/** O4.2c - Controlo de Relatorios.
 *  UC4.2 - graficos de consumo (linha ao longo do tempo + barras por zona).
 *  UC4.3 - relatorio de custo e poupanca (analise financeira). */
@Service
public class ControloRelatorios {
    private final ControloGestaoPostes gestaoPostes;
    private final ControloConfiguracao configuracao;
    private final LeituraRepository leituraRepo;

    public ControloRelatorios(ControloGestaoPostes gestaoPostes, ControloConfiguracao configuracao,
                              LeituraRepository leituraRepo) {
        this.gestaoPostes = gestaoPostes;
        this.configuracao = configuracao;
        this.leituraRepo = leituraRepo;
    }

    // ---------- UC4.2 - GRAFICO: consumo ao longo do tempo ----------
    /** Agrupa as leituras em "fatias" sequenciais e soma a potencia de cada fatia.
     *  Cada ponto e' um instante; o valor e' a potencia total dos postes nesse instante. */
    public List<PontoGrafico> consumoAoLongoDoTempo() {
        List<Leitura> todas = leituraRepo.findAll();
        // Eixo = HORA SIMULADA (0-23). Para cada hora e cada poste, usamos a
        // leitura MAIS RECENTE desse poste nessa hora. Somamos a potencia dos
        // postes SEM avaria (potencia > 0). Postes avariados (0W) nao somam,
        // por isso a noite com 1 avariado da' 11x80=880W em vez de 960W.
        // chave externa: hora ; chave interna: posteId -> ultima leitura dessa hora
        Map<Integer, Map<Long, Leitura>> porHoraPoste = new java.util.TreeMap<>();
        for (Leitura l : todas) {
            Integer h = l.getHoraSimulada();
            if (h == null) continue;
            porHoraPoste.computeIfAbsent(h, k -> new java.util.HashMap<>());
            Map<Long, Leitura> doPoste = porHoraPoste.get(h);
            Leitura anterior = doPoste.get(l.getPosteId());
            // ficar com a leitura mais recente (instante maior) desse poste nessa hora
            if (anterior == null || l.getInstante().isAfter(anterior.getInstante())) {
                doPoste.put(l.getPosteId(), l);
            }
        }
        List<PontoGrafico> pontos = new java.util.ArrayList<>();
        for (Map.Entry<Integer, Map<Long, Leitura>> e : porHoraPoste.entrySet()) {
            double total = 0;
            for (Leitura l : e.getValue().values()) {
                if (l.getPotencia() > 0) {     // so' postes sem avaria
                    total += l.getPotencia();
                }
            }
            pontos.add(new PontoGrafico(String.format("%02dh", e.getKey()), total));
        }
        return pontos;
    }

    // ---------- UC4.2 - GRAFICO: consumo por zona ----------
    public List<ConsumoPorZona> consumoPorZona() {
        Map<String, Double> porZona = new LinkedHashMap<>();
        for (Poste p : gestaoPostes.listar()) {
            porZona.merge(p.getZona(), p.getConsumoAcumulado(), Double::sum);
        }
        return porZona.entrySet().stream()
                .map(e -> new ConsumoPorZona(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    // ---------- UC4.3 - RELATORIO de custo e poupanca ----------
    /** Poupanca = consumo real vs cenario "todos os postes sempre a 100%".
     *  Consumo maximo: cada poste a 80W (potencia maxima) por cada leitura registada. */
    public DadosRelatorio relatorio() {
        List<Poste> postes = gestaoPostes.listar();
        double consumoReal = postes.stream().mapToDouble(Poste::getConsumoAcumulado).sum();

        // cenario "sempre a 100%": cada leitura teria gasto 80W (0.08 kWh) em vez do real
        long nLeituras = leituraRepo.count();
        double consumoMaximo = nLeituras * (80.0 / 1000.0); // 80W por leitura -> kWh

        Configuracao cfg = configuracao.obter();
        return new DadosRelatorio(consumoReal, Math.max(consumoMaximo, consumoReal),
                cfg.getPrecoKwh(), cfg.getCo2PorKwh());
    }

    // ---------- UC4.3 - RELATORIO por poste ----------
    public List<RelatorioPorPoste> relatorioPorPoste() {
        Configuracao cfg = configuracao.obter();
        // contar leituras por poste (para o cenario "sempre a 100%")
        Map<Long, Long> leiturasPorPoste = new HashMap<>();
        for (Leitura l : leituraRepo.findAll()) {
            leiturasPorPoste.merge(l.getPosteId(), 1L, Long::sum);
        }
        List<RelatorioPorPoste> linhas = new ArrayList<>();
        for (Poste p : gestaoPostes.listar()) {
            double real = p.getConsumoAcumulado();
            long n = leiturasPorPoste.getOrDefault(p.getId(), 0L);
            double maximo = n * (80.0 / 1000.0);   // 80W por leitura -> kWh
            maximo = Math.max(maximo, real);
            linhas.add(new RelatorioPorPoste(p.getId(), p.getZona(), real, maximo, cfg.getPrecoKwh()));
        }
        return linhas;
    }
}
