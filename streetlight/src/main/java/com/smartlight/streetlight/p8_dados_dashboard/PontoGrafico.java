package com.smartlight.streetlight.p8_dados_dashboard;

/** Um ponto do grafico de consumo ao longo do tempo (UC4.2).
 *  etiqueta = momento (ex: hora); valor = potencia total nesse momento. */
public class PontoGrafico {
    private String etiqueta;
    private double valor;
    public PontoGrafico(String etiqueta, double valor) { this.etiqueta = etiqueta; this.valor = valor; }
    public String getEtiqueta() { return etiqueta; }
    public double getValor() { return valor; }
}
