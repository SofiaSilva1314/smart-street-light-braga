package com.smartlight.streetlight.p12_interfaces_consulta;

import com.smartlight.streetlight.p4_controladores_dashboard.ControloExportacao;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/** O2.2i (extensao) - Interface de Exportacao CSV (UC5.3).
 *  Devolve ficheiros .csv para download. */
@RestController
@RequestMapping("/api/exportar")
public class InterfaceExportacao {
    private final ControloExportacao exportacao;

    public InterfaceExportacao(ControloExportacao exportacao) {
        this.exportacao = exportacao;
    }

    @GetMapping("/leituras")
    public ResponseEntity<String> leituras() {
        return csv(exportacao.leiturasCSV(), "leituras.csv");
    }

    @GetMapping("/postes")
    public ResponseEntity<String> postes() {
        return csv(exportacao.postesCSV(), "postes.csv");
    }

    private ResponseEntity<String> csv(String conteudo, String nome) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nome + "\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(conteudo);
    }
}
