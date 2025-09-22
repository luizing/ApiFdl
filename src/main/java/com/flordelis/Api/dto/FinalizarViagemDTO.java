package com.flordelis.Api.dto;

import com.flordelis.Api.model.ItemAvariado;
import com.flordelis.Api.model.ItemVenda;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.util.List;

@Getter
public class FinalizarViagemDTO {

    private List<ItemVenda> precos;
    private List<ItemAvariado> avariados;
    @PositiveOrZero(message = "O retorno não pode ser negativo")
    private int retorno;
    @PositiveOrZero(message = "O bônus não pode ser negativo")
    private int bonus;
    @PositiveOrZero(message = "Os quilômetros não podem ser negativos")
    private int kms;
}
