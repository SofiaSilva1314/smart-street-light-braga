package com.smartlight.streetlight.p6_dados_monitorizacao;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** O1.1.1d / O2.1.1d - Dados de Leitura (recebida e persistida). */
@Entity
public class Leitura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long posteId;
    private double luminosidade;   // 0-100 (luz ambiente)
    private double potencia;       // Watts
    private int brilho;            // brilho aplicado 0-100
    private Integer horaSimulada;  // hora do dia simulada (0-23), p/ graficos
    private LocalDateTime instante = LocalDateTime.now();

    public Leitura() {}
    public Leitura(Long posteId, double luminosidade, double potencia, int brilho) {
        this.posteId = posteId; this.luminosidade = luminosidade;
        this.potencia = potencia; this.brilho = brilho;
    }
    public Long getId() { return id; }
    public Long getPosteId() { return posteId; }
    public void setPosteId(Long posteId) { this.posteId = posteId; }
    public double getLuminosidade() { return luminosidade; }
    public void setLuminosidade(double v) { this.luminosidade = v; }
    public double getPotencia() { return potencia; }
    public void setPotencia(double v) { this.potencia = v; }
    public int getBrilho() { return brilho; }
    public void setBrilho(int v) { this.brilho = v; }
    public Integer getHoraSimulada() { return horaSimulada; }
    public void setHoraSimulada(Integer h) { this.horaSimulada = h; }
    public LocalDateTime getInstante() { return instante; }
    public void setInstante(LocalDateTime v) { this.instante = v; }
}
