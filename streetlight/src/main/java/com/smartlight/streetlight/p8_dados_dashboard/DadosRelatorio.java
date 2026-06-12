package com.smartlight.streetlight.p8_dados_dashboard;

/** O4.2d - Dados de Relatorio (UC4.3). Custo, CO2 e POUPANCA.
 *  A poupanca compara o consumo real com o cenario "luz sempre a 100%". */
public class DadosRelatorio {
    private double consumoRealKwh;
    private double consumoMaximoKwh;   // se estivesse sempre a 100%
    private double poupancaKwh;        // diferenca
    private double custoEuros;
    private double custoMaximoEuros;
    private double poupancaEuros;
    private double co2EvitadoKg;
    private double percentagemPoupanca;

    public DadosRelatorio(double real, double maximo, double precoKwh, double co2PorKwh) {
        this.consumoRealKwh = real;
        this.consumoMaximoKwh = maximo;
        this.poupancaKwh = Math.max(0, maximo - real);
        this.custoEuros = real * precoKwh;
        this.custoMaximoEuros = maximo * precoKwh;
        this.poupancaEuros = Math.max(0, this.custoMaximoEuros - this.custoEuros);
        this.co2EvitadoKg = this.poupancaKwh * co2PorKwh;
        this.percentagemPoupanca = maximo > 0 ? (this.poupancaKwh / maximo) * 100 : 0;
    }
    public double getConsumoRealKwh() { return consumoRealKwh; }
    public double getConsumoMaximoKwh() { return consumoMaximoKwh; }
    public double getPoupancaKwh() { return poupancaKwh; }
    public double getCustoEuros() { return custoEuros; }
    public double getCustoMaximoEuros() { return custoMaximoEuros; }
    public double getPoupancaEuros() { return poupancaEuros; }
    public double getCo2EvitadoKg() { return co2EvitadoKg; }
    public double getPercentagemPoupanca() { return percentagemPoupanca; }
}
