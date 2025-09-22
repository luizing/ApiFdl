package com.flordelis.Api.dto;

import com.flordelis.Api.model.ItemAvariado;
import com.flordelis.Api.model.ItemVenda;
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
