package com.smartlight.streetlight.p5_controladores_administracao;

import com.smartlight.streetlight.p7_dados_alertas.Regra;
import com.smartlight.streetlight.p7_dados_alertas.RegraRepository;
import com.smartlight.streetlight.p9_dados_administracao.Configuracao;
import com.smartlight.streetlight.p9_dados_administracao.ConfiguracaoRepository;
import org.springframework.stereotype.Service;

/** O5.1c - Controlo de Configuracao (UC5.1 regras/thresholds, 5.2 utilizadores, 5.3 exportar).
 *  Associacoes (4SRS): O5.1d (Configuracao), O5.1i (Interface), O3.2.1d (Regra). */
@Service
public class ControloConfiguracao {
    private final ConfiguracaoRepository configRepo;
    private final RegraRepository regraRepo;

    public ControloConfiguracao(ConfiguracaoRepository configRepo, RegraRepository regraRepo) {
        this.configRepo = configRepo;
        this.regraRepo = regraRepo;
    }

    public Configuracao obter() {
        return configRepo.findAll().stream().findFirst().orElseGet(() -> configRepo.save(new Configuracao()));
    }
    public Configuracao guardar(Configuracao c) { return configRepo.save(c); }

    /** UC5.1 - configurar regra/thresholds. */
    public Regra guardarRegra(Regra r) { return regraRepo.save(r); }
}
