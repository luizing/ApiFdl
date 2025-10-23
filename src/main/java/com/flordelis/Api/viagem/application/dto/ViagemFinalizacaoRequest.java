package com.flordelis.Api.viagem.application.dto;

import com.flordelis.Api.viagem.domain.model.Despesa;
import com.flordelis.Api.viagem.domain.model.ItemAvariado;
import com.flordelis.Api.viagem.domain.model.ItemVendido;

import java.math.BigDecimal;
import java.util.List;

public record ViagemFinalizacaoRequest(
    List<ItemVendido> itensVendidos,
    List<ItemAvariado> itensAvariados,
    List<Despesa> despesas,
    int itensRetorno,
    int itensBonus,
    int quilometragem
){
    public BigDecimal calcularValorFinal(){
        BigDecimal totalDespesas = this.despesas().stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalVendas = this.itensVendidos().stream()
                .map(item -> item.getValor().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  (totalVendas.subtract(totalDespesas));
    }

    public int calcularQtdVendida(){
        return this.itensVendidos().stream()
                .mapToInt(ItemVendido::getQuantidade)
                .sum();
    }

    public int calcularQtdAvariada(){
        return this.itensAvariados().stream()
                .mapToInt(ItemAvariado::getQuantidade)
                .sum();
    }

}