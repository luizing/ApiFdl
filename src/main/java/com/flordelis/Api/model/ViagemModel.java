package com.flordelis.Api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_viagens")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    private String rota;
    private int carga;
    private float valor;

    public ViagemModel() {
    }

    public ViagemModel(String rota, Date data, int carga, float valor) {
        this.rota = rota;
        this.data = data;
        this.carga = carga;
        this.valor = valor;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
