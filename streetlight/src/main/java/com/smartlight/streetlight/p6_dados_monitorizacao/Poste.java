package com.smartlight.streetlight.p6_dados_monitorizacao;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** O2.3.1d - Dados de Poste. Entidade que representa um poste de iluminacao. */
@Entity
public class Poste {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;   // morada / local do poste
    private String zona;   // agrupamento (Centro, Norte, Sul, Universidade)
    private double latitude;
    private double longitude;
    private String estado = "DESLIGADO";   // DESLIGADO, LIGADO, FALHA
    private int brilho = 0;                 // 0-100
    private double consumoAcumulado = 0;    // kWh acumulado
    private double ultimaLuminosidade = 0;  // ultima luz lida
    private LocalDateTime ultimaAtualizacao; // quando recebeu a ultima leitura

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getBrilho() { return brilho; }
    public void setBrilho(int brilho) { this.brilho = brilho; }
    public double getConsumoAcumulado() { return consumoAcumulado; }
    public void setConsumoAcumulado(double v) { this.consumoAcumulado = v; }
    public double getUltimaLuminosidade() { return ultimaLuminosidade; }
    public void setUltimaLuminosidade(double v) { this.ultimaLuminosidade = v; }
    public LocalDateTime getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(LocalDateTime v) { this.ultimaAtualizacao = v; }
}
