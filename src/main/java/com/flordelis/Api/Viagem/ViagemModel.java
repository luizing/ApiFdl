package com.flordelis.Api.Viagem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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
    private int avariados;

    // Cria a viagem com os atributos disponiveis antes dela ser concluida.
    public ViagemModel(String rota, Date data, int carga) {
        this.rota = rota;
        this.data = data;
        this.carga = carga;
    }

}
