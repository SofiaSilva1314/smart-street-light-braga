package com.smartlight.streetlight.p10_interfaces_operacao;

import com.smartlight.streetlight.p1_controladores_monitorizacao.ControloMonitorizacaoIoT;
import com.smartlight.streetlight.p3_controladores_alertas.ControloAlertas;
import com.smartlight.streetlight.p3_controladores_alertas.MotorDecisao;
import com.smartlight.streetlight.p6_dados_monitorizacao.Leitura;
import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p6_dados_monitorizacao.PosteRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/** O1.1.1i - Interface de Comunicacao com Postes (rececao de leituras via API).
 *  E' por aqui que o simulador Python envia os dados dos postes. */
@RestController
@RequestMapping("/api/leituras")
public class InterfaceRececaoLeituras {

    private static final double POTENCIA_MAXIMA = 80.0; // Watts a 100% de brilho

    private final ControloMonitorizacaoIoT monitorizacao;
    private final MotorDecisao motorDecisao;
    private final ControloAlertas controloAlertas;
    private final PosteRepository posteRepo;

    public InterfaceRececaoLeituras(ControloMonitorizacaoIoT monitorizacao,
                                    MotorDecisao motorDecisao,
                                    ControloAlertas controloAlertas,
                                    PosteRepository posteRepo) {
        this.monitorizacao = monitorizacao;
        this.motorDecisao = motorDecisao;
        this.controloAlertas = controloAlertas;
        this.posteRepo = posteRepo;
    }

    @PostMapping
    public Leitura receber(@RequestBody Leitura leitura) {
        // A potencia que o simulador envia serve para DETETAR AVARIA:
        // se o poste reporta 0W mas devia estar aceso (noite), e' avaria.
        double potenciaReportada = leitura.getPotencia();
        Poste p = posteRepo.findById(leitura.getPosteId()).orElse(null);

        // Se o poste JA' esta' em FALHA, fica avariado ate' ser resolvido no painel.
        if (p != null && "FALHA".equals(p.getEstado())) {
            leitura.setBrilho(0);
            leitura.setPotencia(0);
            p.setUltimaLuminosidade(leitura.getLuminosidade());
            p.setUltimaAtualizacao(LocalDateTime.now());
            posteRepo.save(p);
            return monitorizacao.receberLeitura(leitura);
        }

        // UC3.2.1/3.2.2 - o motor decide o brilho a partir da luz
        int brilho = motorDecisao.decidirBrilho(leitura.getLuminosidade());
        boolean avaria = potenciaReportada == 0 && leitura.getLuminosidade() < 20;

        // A POTENCIA passa a ser PROPORCIONAL AO BRILHO (100%=80W, 50%=40W, 0%=0W).
        // Se ha' avaria, o poste nao gasta nada (0W).
        double potenciaReal = avaria ? 0 : (brilho / 100.0) * POTENCIA_MAXIMA;
        leitura.setBrilho(avaria ? 0 : brilho);
        leitura.setPotencia(potenciaReal);

        if (p != null) {
            p.setEstado(avaria ? "FALHA" : (brilho > 0 ? "LIGADO" : "DESLIGADO"));
            p.setBrilho(avaria ? 0 : brilho);
            p.setUltimaLuminosidade(leitura.getLuminosidade());
            p.setUltimaAtualizacao(LocalDateTime.now());
            posteRepo.save(p);
        }

        Leitura guardada = monitorizacao.receberLeitura(leitura);  // UC1.1.1
        // para a deteccao de alerta, usamos a potencia REPORTADA (a real do "sensor")
        Leitura paraAlerta = new Leitura(leitura.getPosteId(), leitura.getLuminosidade(),
                potenciaReportada, brilho);
        controloAlertas.avaliar(paraAlerta);                       // UC3.1.1
        return guardada;
    }
}
