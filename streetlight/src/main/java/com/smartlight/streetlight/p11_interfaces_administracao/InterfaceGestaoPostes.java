package com.smartlight.streetlight.p11_interfaces_administracao;

import com.smartlight.streetlight.p2_controladores_dados.ControloGestaoPostes;
import com.smartlight.streetlight.p6_dados_monitorizacao.Poste;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** O2.3.1i - Interface de Gestao de Postes (CRUD de postes). */
@RestController
@RequestMapping("/api/postes")
public class InterfaceGestaoPostes {
    private final ControloGestaoPostes gestao;

    public InterfaceGestaoPostes(ControloGestaoPostes gestao) {
        this.gestao = gestao;
    }

    @PostMapping
    public Poste registar(@RequestBody Poste p) { return gestao.registar(p); }

    @GetMapping
    public List<Poste> listar() { return gestao.listar(); }

    @GetMapping("/{id}")
    public Poste obter(@PathVariable Long id) { return gestao.obter(id); }

    @PutMapping("/{id}")
    public Poste editar(@PathVariable Long id, @RequestBody Poste p) { return gestao.editar(id, p); }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) { gestao.remover(id); }
}
