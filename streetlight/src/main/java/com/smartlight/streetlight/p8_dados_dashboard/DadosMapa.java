package com.smartlight.streetlight.p8_dados_dashboard;

/** O4.1.1d - Dados do Mapa. DTO de visualizacao (nao e' tabela). */
public class DadosMapa {
    private Long posteId;
    private String nome;
    private String zona;
    private double latitude;
    private double longitude;
    private String estado;
    private String cor;          // verde/amarelo/vermelho
    private int brilho;
    private double luminosidade;
    private String ultimaAtualizacao;

    public DadosMapa(Long posteId, String nome, String zona, double lat, double lng, String estado,
                     String cor, int brilho, double luminosidade, String ultimaAtualizacao) {
        this.posteId = posteId; this.nome = nome; this.zona = zona; this.latitude = lat; this.longitude = lng;
        this.estado = estado; this.cor = cor; this.brilho = brilho;
        this.luminosidade = luminosidade; this.ultimaAtualizacao = ultimaAtualizacao;
    }
    public Long getPosteId() { return posteId; }
    public String getNome() { return nome; }
    public String getZona() { return zona; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getEstado() { return estado; }
    public String getCor() { return cor; }
    public int getBrilho() { return brilho; }
    public double getLuminosidade() { return luminosidade; }
    public String getUltimaAtualizacao() { return ultimaAtualizacao; }
}
