package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.Despesa;
import com.flordelis.Api.viagem.domain.model.ItemAvariado;
import com.flordelis.Api.viagem.domain.model.ItemVendido;
import com.flordelis.Api.viagem.domain.model.Viagem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ViagemResponse(
        Long id,
        LocalDate data,
        String rota,
        int carga,
        long veiculoId,
        boolean finalizada,
        List<ItemVendido> itensVendidos,
        List<ItemAvariado> itensAvariados,
        int itensRetorno,
        int itensBonificacao,
        int quilometragem,
        List<Despesa> despesas,
        BigDecimal valorFinal
) {

    public static ViagemResponse fromEntity(Viagem viagem) {
        return new ViagemResponse(
                viagem.getId(),
                viagem.getDataSaida(),
                viagem.getRota(),
                viagem.getCarga(),
                viagem.getVeiculoId(),
                viagem.isFinalizada(),
                viagem.getItensVendidos(),
                viagem.getItensAvariados(),
                viagem.getItensRetorno(),
                viagem.getItensBonificacao(),
                viagem.getQuilometragem(),
                viagem.getDespesas(),
                viagem.getValorFinal()
        );
    }
}