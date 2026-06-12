package com.smartlight.streetlight.p8_dados_dashboard;

/** Linha do relatorio de custo e poupanca, por poste (UC4.3). */
public class RelatorioPorPoste {
    private Long posteId;
    private String zona;
    private double consumoRealKwh;
    private double consumoMaximoKwh;
    private double poupancaKwh;
    private double custoEuros;
    private double poupancaEuros;
    private double percentagemPoupanca;

    public RelatorioPorPoste(Long posteId, String zona, double real, double maximo,
                             double precoKwh) {
        this.posteId = posteId;
        this.zona = zona;
        this.consumoRealKwh = real;
        this.consumoMaximoKwh = maximo;
        this.poupancaKwh = Math.max(0, maximo - real);
        this.custoEuros = real * precoKwh;
        this.poupancaEuros = Math.max(0, (maximo - real) * precoKwh);
        this.percentagemPoupanca = maximo > 0 ? (this.poupancaKwh / maximo) * 100 : 0;
    }
    public Long getPosteId() { return posteId; }
    public String getZona() { return zona; }
    public double getConsumoRealKwh() { return consumoRealKwh; }
    public double getConsumoMaximoKwh() { return consumoMaximoKwh; }
    public double getPoupancaKwh() { return poupancaKwh; }
    public double getCustoEuros() { return custoEuros; }
    public double getPoupancaEuros() { return poupancaEuros; }
    public double getPercentagemPoupanca() { return percentagemPoupanca; }
}
