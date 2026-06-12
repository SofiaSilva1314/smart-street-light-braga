package com.smartlight.streetlight.p3_controladores_alertas;

import com.smartlight.streetlight.p6_dados_monitorizacao.Leitura;
import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p6_dados_monitorizacao.PosteRepository;
import com.smartlight.streetlight.p7_dados_alertas.Alerta;
import com.smartlight.streetlight.p7_dados_alertas.AlertaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** O3.1.1c - Controlo de Alertas.
 *  Deteta situacao critica (UC3.1.1), notifica (UC3.1.2), regista historico (UC3.1.3). */
@Service
public class ControloAlertas {
    private final AlertaRepository alertaRepo;
    private final PosteRepository posteRepo;

    public ControloAlertas(AlertaRepository alertaRepo, PosteRepository posteRepo) {
        this.alertaRepo = alertaRepo;
        this.posteRepo = posteRepo;
    }

    /** UC3.1.1 - cria alerta de FALHA se o poste esta' sem potencia de noite.
     *  Evita duplicar: so' cria se nao existir ja' um alerta ABERTO desse poste. */
    public Alerta avaliar(Leitura leitura) {
        boolean avaria = leitura.getPotencia() == 0 && leitura.getLuminosidade() < 20;
        if (!avaria) return null;

        boolean jaExiste = alertaRepo.findAll().stream()
                .anyMatch(a -> a.getPosteId().equals(leitura.getPosteId())
                        && a.getEstado().equals("ABERTO"));
        if (jaExiste) return null;

        Alerta a = new Alerta(leitura.getPosteId(), "FALHA", "ALTA",
                "Poste sem potencia durante a noite - possivel avaria");
        return alertaRepo.save(a);  // UC3.1.3
    }

    public List<Alerta> listar() { return alertaRepo.findAll(); }

    /** Resolve o alerta E repoe o poste a funcionar (simula equipa que reparou). */
    public Alerta resolver(Long id) {
        Alerta a = alertaRepo.findById(id).orElseThrow();
        a.setEstado("RESOLVIDO");
        alertaRepo.save(a);

        // repor o poste: sai de FALHA, volta a DESLIGADO (a proxima leitura decide o brilho)
        Poste p = posteRepo.findById(a.getPosteId()).orElse(null);
        if (p != null && "FALHA".equals(p.getEstado())) {
            p.setEstado("DESLIGADO");
            posteRepo.save(p);
        }
        return a;
    }
}
