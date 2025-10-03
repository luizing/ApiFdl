package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.Despesa;
import com.flordelis.Api.viagem.domain.model.ItemAvariado;
import com.flordelis.Api.viagem.domain.model.ItemVenda;

import java.math.BigDecimal;
import java.util.List;

public record FinalizarViagemDTO (
    List<ItemVenda> precos,
    List<ItemAvariado> avariados,
    List<Despesa> despesas,
    int retorno,
    int bonus,
    int kms,
    BigDecimal valorFinal
){}