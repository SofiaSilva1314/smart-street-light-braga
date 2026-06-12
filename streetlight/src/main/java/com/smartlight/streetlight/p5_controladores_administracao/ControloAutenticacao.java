package com.smartlight.streetlight.p5_controladores_administracao;

import com.smartlight.streetlight.p9_dados_administracao.Utilizador;
import com.smartlight.streetlight.p9_dados_administracao.UtilizadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Controlo de Autenticacao e gestao de utilizadores (UC5.2).
 *  Pertence ao P5 - Controladores de Administracao. */
@Service
public class ControloAutenticacao {
    private final UtilizadorRepository repo;

    public ControloAutenticacao(UtilizadorRepository repo) {
        this.repo = repo;
    }

    /** Valida credenciais. Devolve o utilizador se acertar, ou null. */
    public Utilizador autenticar(String username, String password) {
        return repo.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    // UC5.2 - gerir utilizadores
    public List<Utilizador> listar() { return repo.findAll(); }
    public Utilizador criar(Utilizador u) { return repo.save(u); }
    public void remover(Long id) { repo.deleteById(id); }
}
