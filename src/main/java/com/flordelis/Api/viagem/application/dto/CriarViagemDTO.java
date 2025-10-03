package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.ViagemModel;


import java.time.LocalDate;

public record CriarViagemDTO (
    LocalDate data,
    String rota,
    int carga,
    long veiculoId
){
    public ViagemModel toEntity(){
        return new ViagemModel(data, rota, carga, veiculoId);
    }
}
