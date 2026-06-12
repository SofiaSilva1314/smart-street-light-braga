package com.smartlight.streetlight.p8_dados_dashboard;

/** Consumo agregado por zona (grafico de barras, UC4.2). */
public class ConsumoPorZona {
    private String zona;
    private double consumo;
    public ConsumoPorZona(String zona, double consumo) { this.zona = zona; this.consumo = consumo; }
    public String getZona() { return zona; }
    public double getConsumo() { return consumo; }
}
