package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.Viagem;


import java.time.LocalDate;

public record ViagemCriacaoRequest(
    LocalDate data,
    String rota,
    int carga,
    long veiculoId
){
    public Viagem toEntity(){
        return new Viagem(data, rota, carga, veiculoId);
    }
}
