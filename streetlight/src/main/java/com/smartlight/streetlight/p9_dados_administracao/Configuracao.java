package com.smartlight.streetlight.p9_dados_administracao;

import jakarta.persistence.*;

/** O5.1d - Dados de Configuracao. */
@Entity
public class Configuracao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precoKwh = 0.15;       // euros por kWh
    private double co2PorKwh = 0.4;        // kg CO2 por kWh
    private boolean modoAutomatico = true;

    public Long getId() { return id; }
    public double getPrecoKwh() { return precoKwh; }
    public void setPrecoKwh(double v) { this.precoKwh = v; }
    public double getCo2PorKwh() { return co2PorKwh; }
    public void setCo2PorKwh(double v) { this.co2PorKwh = v; }
    public boolean isModoAutomatico() { return modoAutomatico; }
    public void setModoAutomatico(boolean v) { this.modoAutomatico = v; }
}
