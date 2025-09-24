package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.ViagemModel;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CriarViagemDTO {
    private LocalDate data;
    private String rota;
    private int carga;
    private long veiculoId;

    public ViagemModel converter(){
        return new ViagemModel(data, rota, carga, veiculoId);
    }

    @Override
    public String toString(){
        return "{data -> " + data +
                "; rota -> " + rota +
                "; carga -> " + carga +
                "; veiculoId -> " + veiculoId + "}";
    }
}
