package com.smartlight.streetlight.p2_controladores_dados;

import com.smartlight.streetlight.p6_dados_monitorizacao.*;
import org.springframework.stereotype.Service;

/** O2.1.1c - Controlo de Armazenamento.
 *  Persiste a leitura (UC2.1.1), associa ao poste (UC2.1.2) e
 *  calcula o consumo acumulado (UC2.1.3).
 *  Associacao (4SRS): O2.1.1d (Leitura persistida). */
@Service
public class ControloArmazenamento {
    private final LeituraRepository leituraRepo;
    private final PosteRepository posteRepo;

    public ControloArmazenamento(LeituraRepository leituraRepo, PosteRepository posteRepo) {
        this.leituraRepo = leituraRepo;
        this.posteRepo = posteRepo;
    }

    public Leitura guardar(Leitura leitura) {
        Leitura guardada = leituraRepo.save(leitura);          // UC2.1.1
        Poste poste = posteRepo.findById(leitura.getPosteId()).orElse(null);  // UC2.1.2
        if (poste != null) {
            // UC2.1.3 - consumo acumulado: potencia(W) -> kWh aproximado por leitura
            double incremento = leitura.getPotencia() / 1000.0; // simplificado
            poste.setConsumoAcumulado(poste.getConsumoAcumulado() + incremento);
            poste.setBrilho(leitura.getBrilho());
            posteRepo.save(poste);
        }
        return guardada;
    }
}
