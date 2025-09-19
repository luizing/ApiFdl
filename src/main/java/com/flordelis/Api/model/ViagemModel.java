package com.flordelis.Api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotNull(message = "A data não pode ser nula")
    private LocalDate data;
    @NotBlank(message = "A rota não pode estar vazia")
    private String rota;
    @NotNull(message = "A carga não pode ser nula")
    private int carga;

    private boolean finalizada = false;

    private float valor;
    private int avariados;

    public ViagemModel(LocalDate data, String rota, int carga){
        this.data = data;
        this.rota = rota;
        this.carga = carga;
    }

}
