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
){
    public BigDecimal calcularValorFinal(){
        BigDecimal totalDespesas = this.despesas().stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalVendas = this.precos().stream()
                .map(item -> item.getValor().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  (totalVendas.subtract(totalDespesas));
    }

    public int calcularQtdVendida(){
        return this.precos().stream()
                .mapToInt(ItemVenda::getQuantidade)
                .sum();
    }

    public int calcularQtdAvariada(){
        return this.avariados().stream()
                .mapToInt(ItemAvariado::getQuantidade)
                .sum();
    }


}