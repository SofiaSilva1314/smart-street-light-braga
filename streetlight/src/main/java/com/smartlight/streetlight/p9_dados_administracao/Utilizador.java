package com.smartlight.streetlight.p9_dados_administracao;

import jakarta.persistence.*;

/** Entidade Utilizador. Suporta UC5.2 (gerir utilizadores) e o login.
 *  Pertence ao package P9 - Dados de Administracao (gestao e' do Administrador). */
@Entity
@Table(name = "utilizador")
public class Utilizador {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;   // texto simples (projeto academico; ver nota no README)
    private String perfil;     // OPERADOR ou ADMINISTRADOR

    public Utilizador() {}
    public Utilizador(String username, String password, String perfil) {
        this.username = username; this.password = password; this.perfil = perfil;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String v) { this.username = v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { this.password = v; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String v) { this.perfil = v; }
}
