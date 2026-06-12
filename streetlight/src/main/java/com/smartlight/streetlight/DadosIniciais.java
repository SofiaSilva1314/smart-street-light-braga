package com.smartlight.streetlight;

import com.smartlight.streetlight.p6_dados_monitorizacao.*;
import com.smartlight.streetlight.p7_dados_alertas.*;
import com.smartlight.streetlight.p9_dados_administracao.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Cria dados de exemplo ao arrancar: 12 postes em locais REAIS de Braga,
 *  agrupados em 4 zonas (Centro, Norte, Sul, Universidade). */
@Component
public class DadosIniciais implements CommandLineRunner {
    private final PosteRepository posteRepo;
    private final RegraRepository regraRepo;
    private final ConfiguracaoRepository configRepo;
    private final UtilizadorRepository utilizadorRepo;

    public DadosIniciais(PosteRepository posteRepo, RegraRepository regraRepo,
                         ConfiguracaoRepository configRepo, UtilizadorRepository utilizadorRepo) {
        this.posteRepo = posteRepo; this.regraRepo = regraRepo;
        this.configRepo = configRepo; this.utilizadorRepo = utilizadorRepo;
    }

    @Override
    public void run(String... args) {
        if (posteRepo.count() == 0) {
            // {nome (morada), zona (agrupamento), latitude, longitude}
            Object[][] locais = {
                {"Avenida Central",       "Centro",       41.5503, -8.4265},
                {"Praça da República",    "Centro",       41.5510, -8.4258},
                {"Sé de Braga",           "Centro",       41.5503, -8.4275},
                {"Rua do Souto",          "Centro",       41.5508, -8.4262},
                {"Largo do Paço",         "Norte",        41.5530, -8.4268},
                {"Bom Jesus",             "Norte",        41.5547, -8.3776},
                {"Campo das Hortas",      "Sul",          41.5497, -8.4290},
                {"Estádio 1º de Maio",    "Sul",          41.5450, -8.4300},
                {"Parque da Ponte",       "Sul",          41.5380, -8.4250},
                {"Estação de Braga",      "Sul",          41.5470, -8.4340},
                {"Universidade do Minho", "Universidade", 41.5608, -8.3970},
                {"Hospital de Braga",     "Universidade", 41.5630, -8.4380},
            };
            for (Object[] l : locais) {
                Poste p = new Poste();
                p.setNome((String) l[0]);
                p.setZona((String) l[1]);
                p.setLatitude((double) l[2]);
                p.setLongitude((double) l[3]);
                posteRepo.save(p);
            }
        }
        if (regraRepo.count() == 0) {
            Regra r = new Regra(); r.setNome("Regra padrao"); regraRepo.save(r);
        }
        if (configRepo.count() == 0) configRepo.save(new Configuracao());
        if (utilizadorRepo.count() == 0) {
            utilizadorRepo.save(new Utilizador("admin", "admin123", "ADMINISTRADOR"));
            utilizadorRepo.save(new Utilizador("operador", "op123", "OPERADOR"));
        }
        System.out.println(">>> Dados iniciais: " + posteRepo.count() + " postes em 4 zonas de Braga, "
                + utilizadorRepo.count() + " utilizadores.");
        System.out.println(">>> Login Admin: admin / admin123  |  Operador: operador / op123");
    }
}
