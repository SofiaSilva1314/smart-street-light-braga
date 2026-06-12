package com.smartlight.streetlight.p10_interfaces_operacao;

import com.smartlight.streetlight.p4_controladores_dashboard.ControloVisualizacao;
import com.smartlight.streetlight.p4_controladores_dashboard.ControloRelatorios;
import com.smartlight.streetlight.p8_dados_dashboard.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** O4.1.1i - Interface de Dashboard e Mapa. */
@RestController
@RequestMapping("/api/dashboard")
public class InterfaceDashboardMapa {
    private final ControloVisualizacao visualizacao;
    private final ControloRelatorios relatorios;

    public InterfaceDashboardMapa(ControloVisualizacao visualizacao, ControloRelatorios relatorios) {
        this.visualizacao = visualizacao;
        this.relatorios = relatorios;
    }

    @GetMapping("/mapa")
    public List<DadosMapa> mapa() { return visualizacao.postesParaMapa(); }

    // UC4.3 - relatorio de custo e poupanca
    @GetMapping("/relatorio")
    public DadosRelatorio relatorio() { return relatorios.relatorio(); }

    // UC4.2 - graficos de consumo
    @GetMapping("/grafico/tempo")
    public List<PontoGrafico> graficoTempo() { return relatorios.consumoAoLongoDoTempo(); }

    @GetMapping("/grafico/zona")
    public List<ConsumoPorZona> graficoZona() { return relatorios.consumoPorZona(); }

    // UC4.3 - relatorio por poste
    @GetMapping("/relatorio/postes")
    public List<RelatorioPorPoste> relatorioPorPoste() { return relatorios.relatorioPorPoste(); }
}
