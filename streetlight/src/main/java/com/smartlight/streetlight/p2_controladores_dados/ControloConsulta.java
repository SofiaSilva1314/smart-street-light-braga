package com.smartlight.streetlight.p2_controladores_dados;

import com.smartlight.streetlight.p6_dados_monitorizacao.Leitura;
import com.smartlight.streetlight.p6_dados_monitorizacao.LeituraRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** O2.2c - Controlo de Consulta (UC2.2 consultar historico).
 *  Associacoes (4SRS): O2.2d (Historico), O2.2i (Interface de Consulta). */
@Service
public class ControloConsulta {
    private final LeituraRepository leituraRepo;

    public ControloConsulta(LeituraRepository leituraRepo) {
        this.leituraRepo = leituraRepo;
    }

    public List<Leitura> historico() { return leituraRepo.findAll(); }
    public List<Leitura> historicoDoPoste(Long posteId) {
        return leituraRepo.findByPosteIdOrderByInstanteDesc(posteId);
    }
}
