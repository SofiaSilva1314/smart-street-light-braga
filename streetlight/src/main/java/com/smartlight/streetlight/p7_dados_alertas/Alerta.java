package com.smartlight.streetlight.p7_dados_alertas;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** O3.1.1d - Dados de Alerta. */
@Entity
public class Alerta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long posteId;
    private String tipo;        // FALHA, CONSUMO_ANORMAL
    private String severidade;  // BAIXA, MEDIA, ALTA
    private String mensagem;
    private String estado = "ABERTO";
    private LocalDateTime instante = LocalDateTime.now();

    public Alerta() {}
    public Alerta(Long posteId, String tipo, String severidade, String mensagem) {
        this.posteId = posteId; this.tipo = tipo; this.severidade = severidade; this.mensagem = mensagem;
    }
    public Long getId() { return id; }
    public Long getPosteId() { return posteId; }
    public String getTipo() { return tipo; }
    public String getSeveridade() { return severidade; }
    public String getMensagem() { return mensagem; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getInstante() { return instante; }
}
