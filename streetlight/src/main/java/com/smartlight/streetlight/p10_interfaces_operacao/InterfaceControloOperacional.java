package com.smartlight.streetlight.p10_interfaces_operacao;

import com.smartlight.streetlight.p3_controladores_alertas.ControloManualPostes;
import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import com.smartlight.streetlight.p7_dados_alertas.ComandoManual;
import org.springframework.web.bind.annotation.*;

/** O3.3.1i - Interface de Controlo Operacional. Permite ao operador comandar um poste. */
@RestController
@RequestMapping("/api/controlo")
public class InterfaceControloOperacional {
    private final ControloManualPostes controloManual;

    public InterfaceControloOperacional(ControloManualPostes controloManual) {
        this.controloManual = controloManual;
    }

    @PostMapping("/comando")
    public Poste comandar(@RequestBody ComandoManual cmd) {
        return controloManual.aplicarComando(cmd);
    }
}
