package com.smartlight.streetlight.p3_controladores_alertas;

import com.smartlight.streetlight.p7_dados_alertas.Regra;
import com.smartlight.streetlight.p7_dados_alertas.RegraRepository;
import org.springframework.stereotype.Service;

/** O3.2.1c - Motor de Decisao.
 *  Avalia o nivel de luz (UC3.2.1), decide o brilho (UC3.2.2) e a acao (UC3.2.3).
 *  Associacoes (4SRS): O3.2.1d (Regra), O1.1.1c (comanda via monitorizacao). */
@Service
public class MotorDecisao {
    private final RegraRepository regraRepo;

    public MotorDecisao(RegraRepository regraRepo) {
        this.regraRepo = regraRepo;
    }

    /** UC3.2.1 + 3.2.2 - dada a luz ambiente, devolve o brilho a aplicar (0-100). */
    public int decidirBrilho(double luminosidade) {
        Regra r = regraRepo.findAll().stream().filter(Regra::isAtiva).findFirst().orElse(new Regra());
        if (luminosidade >= r.getLimiteDia()) return 0;     // dia -> apaga
        if (luminosidade <= r.getLimiteNoite()) return 100; // noite -> maximo
        return 50;                                          // entardecer -> medio
    }
}
