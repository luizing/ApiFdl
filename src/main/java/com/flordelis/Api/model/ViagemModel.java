package com.flordelis.Api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_viagens")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate data;
    @NotNull
    private String rota;
    @NotNull
    private int carga;

    private float valor;
    private int avariados;

    public ViagemModel(LocalDate data, String rota, int carga){
        this.data = data;
        this.rota = rota;
        this.carga = carga;
    }

}
