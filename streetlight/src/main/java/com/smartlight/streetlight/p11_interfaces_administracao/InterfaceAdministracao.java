package com.smartlight.streetlight.p11_interfaces_administracao;

import com.smartlight.streetlight.p5_controladores_administracao.ControloConfiguracao;
import com.smartlight.streetlight.p7_dados_alertas.Regra;
import com.smartlight.streetlight.p9_dados_administracao.Configuracao;
import org.springframework.web.bind.annotation.*;

/** O5.1i - Interface de Administracao (configurar regras e thresholds). */
@RestController
@RequestMapping("/api/admin")
public class InterfaceAdministracao {
    private final ControloConfiguracao config;

    public InterfaceAdministracao(ControloConfiguracao config) {
        this.config = config;
    }

    @GetMapping("/config")
    public Configuracao obterConfig() { return config.obter(); }

    @PutMapping("/config")
    public Configuracao guardarConfig(@RequestBody Configuracao c) { return config.guardar(c); }

    @PostMapping("/regras")
    public Regra guardarRegra(@RequestBody Regra r) { return config.guardarRegra(r); }
}
