package com.smartlight.streetlight.p4_controladores_dashboard;

import com.smartlight.streetlight.p2_controladores_dados.ControloGestaoPostes;
import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p8_dados_dashboard.DadosMapa;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/** O4.1.1c - Controlo de Visualizacao.
 *  Mostra postes no mapa (UC4.1.1), cor por estado (UC4.1.2), tempo real (UC4.1.3). */
@Service
public class ControloVisualizacao {
    private final ControloGestaoPostes gestaoPostes;

    public ControloVisualizacao(ControloGestaoPostes gestaoPostes) {
        this.gestaoPostes = gestaoPostes;
    }

    public List<DadosMapa> postesParaMapa() {
        return gestaoPostes.listar().stream().map(this::converter).collect(Collectors.toList());
    }

    private DadosMapa converter(Poste p) {
        String cor = switch (p.getEstado()) {
            case "FALHA" -> "vermelho";
            case "LIGADO" -> "verde";
            default -> "amarelo";
        };
        String atualizacao = p.getUltimaAtualizacao() == null ? "—"
                : p.getUltimaAtualizacao().toString().replace("T", " ").substring(0, 19);
        return new DadosMapa(p.getId(), p.getNome(), p.getZona(), p.getLatitude(), p.getLongitude(),
                p.getEstado(), cor, p.getBrilho(), p.getUltimaLuminosidade(), atualizacao);
    }
}
