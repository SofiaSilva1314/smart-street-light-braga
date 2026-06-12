package com.smartlight.streetlight.p10_interfaces_operacao;

import com.smartlight.streetlight.p3_controladores_alertas.ControloAlertas;
import com.smartlight.streetlight.p7_dados_alertas.Alerta;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** O3.1.2i - Interface de Notificacao. Mostra alertas ao operador. */
@RestController
@RequestMapping("/api/alertas")
public class InterfaceNotificacao {
    private final ControloAlertas controloAlertas;

    public InterfaceNotificacao(ControloAlertas controloAlertas) {
        this.controloAlertas = controloAlertas;
    }

    @GetMapping
    public List<Alerta> listar() { return controloAlertas.listar(); }

    @PostMapping("/{id}/resolver")
    public Alerta resolver(@PathVariable Long id) { return controloAlertas.resolver(id); }
}
