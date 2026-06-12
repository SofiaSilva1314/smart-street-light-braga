package com.smartlight.streetlight.p11_interfaces_administracao;

import com.smartlight.streetlight.dto.LoginRequest;
import com.smartlight.streetlight.dto.LoginResponse;
import com.smartlight.streetlight.p5_controladores_administracao.ControloAutenticacao;
import com.smartlight.streetlight.p9_dados_administracao.Utilizador;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** Interface de Autenticacao e gestao de utilizadores.
 *  Pertence ao P11 - Interfaces de Administracao. */
@RestController
@RequestMapping("/api/auth")
public class InterfaceAutenticacao {
    private final ControloAutenticacao auth;

    public InterfaceAutenticacao(ControloAutenticacao auth) {
        this.auth = auth;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        Utilizador u = auth.autenticar(req.getUsername(), req.getPassword());
        if (u == null) return new LoginResponse(false, null, "Credenciais invalidas");
        return new LoginResponse(true, u.getPerfil(), "Bem-vindo, " + u.getUsername());
    }

    @GetMapping("/utilizadores")
    public List<Utilizador> listar() { return auth.listar(); }

    @PostMapping("/utilizadores")
    public Utilizador criar(@RequestBody Utilizador u) { return auth.criar(u); }

    @DeleteMapping("/utilizadores/{id}")
    public void remover(@PathVariable Long id) { auth.remover(id); }
}
