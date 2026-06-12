package com.smartlight.streetlight.p1_controladores_monitorizacao;

import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p6_dados_monitorizacao.PosteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** O1.2c - Controlo de Estado dos Postes (UC1.2 monitorizar estado).
 *  Associacao (4SRS): O1.2d (Dados de Estado / Poste). */
@Service
public class ControloEstadoPostes {
    private final PosteRepository posteRepo;

    public ControloEstadoPostes(PosteRepository posteRepo) {
        this.posteRepo = posteRepo;
    }

    public List<Poste> estadoDeTodos() {
        return posteRepo.findAll();
    }
}
