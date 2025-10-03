package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.Despesa;
import com.flordelis.Api.viagem.domain.model.ItemAvariado;
import com.flordelis.Api.viagem.domain.model.ItemVenda;
import com.flordelis.Api.viagem.domain.model.ViagemModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RetornarViagemDTO(
        Long id,
        LocalDate data,
        String rota,
        int carga,
        long veiculoId,
        boolean finalizada,
        List<ItemVenda> precos,
        List<ItemAvariado> avariados,
        int retorno,
        int bonus,
        int kms,
        List<Despesa> despesas,
        BigDecimal valorFinal
) {

    public static RetornarViagemDTO fromEntity(ViagemModel viagem) {
        return new RetornarViagemDTO(
                viagem.getId(),
                viagem.getData(),
                viagem.getRota(),
                viagem.getCarga(),
                viagem.getVeiculoId(),
                viagem.isFinalizada(),
                viagem.getPrecos(),
                viagem.getAvariados(),
                viagem.getRetorno(),
                viagem.getBonus(),
                viagem.getKms(),
                viagem.getDespesas(),
                viagem.getValorFinal()
        );
    }
}