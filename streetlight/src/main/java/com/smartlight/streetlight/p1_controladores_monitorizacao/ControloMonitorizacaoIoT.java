package com.smartlight.streetlight.p1_controladores_monitorizacao;

import com.smartlight.streetlight.p2_controladores_dados.ControloArmazenamento;
import com.smartlight.streetlight.p6_dados_monitorizacao.Leitura;
import org.springframework.stereotype.Service;

/** O1.1.1c - Controlo de Monitorizacao IoT.
 *  Recebe leituras dos postes e encaminha para armazenamento.
 *  Associacoes (4SRS): O1.1.1d (Leitura), O2.1.1c (Controlo de Armazenamento). */
@Service
public class ControloMonitorizacaoIoT {
    private final ControloArmazenamento armazenamento;

    public ControloMonitorizacaoIoT(ControloArmazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    /** Recebe (UC1.1.1), valida (UC1.1.2) e regista (UC1.1.3) uma leitura. */
    public Leitura receberLeitura(Leitura leitura) {
        if (leitura.getPosteId() == null) {
            throw new IllegalArgumentException("Leitura sem poste associado");
        }
        return armazenamento.guardar(leitura);
    }
}
