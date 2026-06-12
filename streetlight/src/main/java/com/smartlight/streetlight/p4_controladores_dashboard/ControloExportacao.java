package com.smartlight.streetlight.p4_controladores_dashboard;

import com.smartlight.streetlight.p6_dados_monitorizacao.*;
import org.springframework.stereotype.Service;
import java.util.List;

/** Controlo de Exportacao (UC5.3 exportar relatorio CSV).
 *  Gera o conteudo CSV a partir dos dados da base de dados. */
@Service
public class ControloExportacao {
    private final LeituraRepository leituraRepo;
    private final PosteRepository posteRepo;

    public ControloExportacao(LeituraRepository leituraRepo, PosteRepository posteRepo) {
        this.leituraRepo = leituraRepo;
        this.posteRepo = posteRepo;
    }

    /** CSV do historico de leituras. */
    public String leiturasCSV() {
        StringBuilder sb = new StringBuilder("id,posteId,luminosidade,potencia,brilho,instante\n");
        for (Leitura l : leituraRepo.findAll()) {
            sb.append(l.getId()).append(',')
              .append(l.getPosteId()).append(',')
              .append(l.getLuminosidade()).append(',')
              .append(l.getPotencia()).append(',')
              .append(l.getBrilho()).append(',')
              .append(l.getInstante()).append('\n');
        }
        return sb.toString();
    }

    /** CSV do estado atual dos postes. */
    public String postesCSV() {
        StringBuilder sb = new StringBuilder("id,nome,zona,estado,brilho,consumoAcumulado,latitude,longitude\n");
        for (Poste p : posteRepo.findAll()) {
            sb.append(p.getId()).append(',')
              .append('"').append(p.getNome()).append('"').append(',')
              .append('"').append(p.getZona()).append('"').append(',')
              .append(p.getEstado()).append(',')
              .append(p.getBrilho()).append(',')
              .append(p.getConsumoAcumulado()).append(',')
              .append(p.getLatitude()).append(',')
              .append(p.getLongitude()).append('\n');
        }
        return sb.toString();
    }
}
