package com.smartlight.streetlight.p3_controladores_alertas;

import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p6_dados_monitorizacao.PosteRepository;
import com.smartlight.streetlight.p7_dados_alertas.ComandoManual;
import org.springframework.stereotype.Service;

/** O3.3.1c - Controlo Manual de Postes.
 *  Selecionar (UC3.3.1), definir brilho (UC3.3.2), confirmar (UC3.3.3).
 *  Associacoes (4SRS): O3.3.1d (Comando), O3.3.1i (Interface de Controlo Operacional). */
@Service
public class ControloManualPostes {
    private final PosteRepository posteRepo;

    public ControloManualPostes(PosteRepository posteRepo) {
        this.posteRepo = posteRepo;
    }

    public Poste aplicarComando(ComandoManual cmd) {
        Poste p = posteRepo.findById(cmd.getPosteId()).orElseThrow();
        p.setBrilho(cmd.getBrilhoPretendido());
        p.setEstado(cmd.getBrilhoPretendido() > 0 ? "LIGADO" : "DESLIGADO");
        return posteRepo.save(p);
    }
}
