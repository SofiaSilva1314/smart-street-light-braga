package com.smartlight.streetlight.dto;

/** DTO - resposta ao login: diz se teve sucesso e qual o perfil. */
public class LoginResponse {
    private boolean sucesso;
    private String perfil;
    private String mensagem;
    public LoginResponse(boolean sucesso, String perfil, String mensagem) {
        this.sucesso = sucesso; this.perfil = perfil; this.mensagem = mensagem;
    }
    public boolean isSucesso() { return sucesso; }
    public String getPerfil() { return perfil; }
    public String getMensagem() { return mensagem; }
}
