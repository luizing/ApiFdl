package com.flordelis.Api.dto;

import com.flordelis.Api.model.ViagemModel;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CriarViagemDTO {
    private LocalDate data;
    private String rota;
    private int carga;

    public ViagemModel converter(){
        return new ViagemModel(data, rota, carga);
    }

    @Override
    public String toString(){
        return "{data -> " + data +
                "; rota -> " + rota +
                "; carga -> " + carga + "}";
    }
}
