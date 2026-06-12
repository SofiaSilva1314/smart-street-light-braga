package com.smartlight.streetlight.p7_dados_alertas;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** O3.3.1d - Dados de Comando Manual. */
@Entity
public class ComandoManual {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long posteId;
    private int brilhoPretendido;
    private String operador;
    private LocalDateTime instante = LocalDateTime.now();

    public ComandoManual() {}
    public ComandoManual(Long posteId, int brilhoPretendido, String operador) {
        this.posteId = posteId; this.brilhoPretendido = brilhoPretendido; this.operador = operador;
    }
    public Long getId() { return id; }
    public Long getPosteId() { return posteId; }
    public int getBrilhoPretendido() { return brilhoPretendido; }
    public String getOperador() { return operador; }
    public LocalDateTime getInstante() { return instante; }
}
