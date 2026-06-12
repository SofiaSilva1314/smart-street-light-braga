package com.smartlight.streetlight.p7_dados_alertas;

import jakarta.persistence.*;

/** O3.2.1d - Dados de Regra. Define thresholds do motor de decisao. */
@Entity
public class Regra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double limiteDia = 70;    // luminosidade acima da qual e' dia -> apagar
    private double limiteNoite = 20;  // luminosidade abaixo da qual e' noite -> 100%
    private boolean ativa = true;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getLimiteDia() { return limiteDia; }
    public void setLimiteDia(double v) { this.limiteDia = v; }
    public double getLimiteNoite() { return limiteNoite; }
    public void setLimiteNoite(double v) { this.limiteNoite = v; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}
