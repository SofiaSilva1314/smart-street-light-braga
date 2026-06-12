package com.smartlight.streetlight.p2_controladores_dados;

import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p6_dados_monitorizacao.PosteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** O2.3.1c - Controlo de Gestao de Postes (UC2.3.1 registar, 2.3.2 editar, 2.3.3 remover).
 *  Associacoes (4SRS): O2.3.1d (Poste), O2.3.1i (Interface de Gestao de Postes). */
@Service
public class ControloGestaoPostes {
    private final PosteRepository posteRepo;

    public ControloGestaoPostes(PosteRepository posteRepo) {
        this.posteRepo = posteRepo;
    }

    public Poste registar(Poste p) { return posteRepo.save(p); }      // UC2.3.1
    public Poste editar(Long id, Poste dados) {                        // UC2.3.2
        Poste p = posteRepo.findById(id).orElseThrow();
        p.setZona(dados.getZona());
        p.setLatitude(dados.getLatitude());
        p.setLongitude(dados.getLongitude());
        return posteRepo.save(p);
    }
    public void remover(Long id) { posteRepo.deleteById(id); }        // UC2.3.3
    public List<Poste> listar() { return posteRepo.findAll(); }
    public Poste obter(Long id) { return posteRepo.findById(id).orElse(null); }
}
