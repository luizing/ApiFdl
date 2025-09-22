package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.ItemAvariado;
import com.flordelis.Api.viagem.domain.model.ItemVenda;
import lombok.Getter;

import java.util.List;

@Getter
public class FinalizarViagemDTO {
    private List<ItemVenda> precos;
    private List<ItemAvariado> avariados;
    private int retorno;
    private int bonus;
    private int kms;
}
