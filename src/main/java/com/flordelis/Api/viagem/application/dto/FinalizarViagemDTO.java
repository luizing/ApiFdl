package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.Despesa;
import com.flordelis.Api.viagem.domain.model.ItemAvariado;
import com.flordelis.Api.viagem.domain.model.ItemVenda;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class FinalizarViagemDTO {
    private List<ItemVenda> precos;
    private List<ItemAvariado> avariados;
    private List<Despesa> despesas;
    private int retorno;
    private int bonus;
    private int kms;
    private BigDecimal valorFinal;

    @Override
    public String toString(){
        return "{precos -> " + precos +
                "; avariados -> " + avariados +
                "; despesas -> " + despesas +
                "; retorno -> " + retorno +
                "; bonus -> " + bonus +
                "; kms -> " + kms +
                "; valorFinal -> " + valorFinal +
                "}";
    }
}
