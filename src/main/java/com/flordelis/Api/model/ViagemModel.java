package com.flordelis.Api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_viagens")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private String rota;
    private int carga;

    private float valor;
    private int avariados;

    // Cria a viagem com os atributos disponiveis antes dela ser concluida.
    public ViagemModel(String rota, LocalDate data, int carga) {
        this.rota = rota;
        this.data = data;
        this.carga = carga;
    }

}
