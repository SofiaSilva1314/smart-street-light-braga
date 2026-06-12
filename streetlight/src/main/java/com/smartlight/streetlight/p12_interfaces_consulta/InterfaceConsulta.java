package com.smartlight.streetlight.p12_interfaces_consulta;

import com.smartlight.streetlight.p2_controladores_dados.ControloConsulta;
import com.smartlight.streetlight.p6_dados_monitorizacao.Leitura;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** O2.2i - Interface de Consulta (historico de leituras). */
@RestController
@RequestMapping("/api/historico")
public class InterfaceConsulta {
    private final ControloConsulta consulta;

    public InterfaceConsulta(ControloConsulta consulta) {
        this.consulta = consulta;
    }

    @GetMapping
    public List<Leitura> historico() { return consulta.historico(); }

    @GetMapping("/poste/{posteId}")
    public List<Leitura> historicoDoPoste(@PathVariable Long posteId) {
        return consulta.historicoDoPoste(posteId);
    }
}
