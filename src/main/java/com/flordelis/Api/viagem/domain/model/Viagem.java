package com.flordelis.Api.viagem.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "tb_viagens")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "A data de saída não pode ser vazia")
    private LocalDate dataSaida;
    @NotBlank(message = "A rota não pode estar vazia")
    private String rota;
    @PositiveOrZero(message = "A carga não pode ser negativa")
    private int carga;
    @Positive(message = "O id do veiculo deve ser positivo")
    private long veiculoId;

    private boolean finalizada;

    @ElementCollection
    private List<ItemVendido> itensVendidos = new ArrayList<>();
    @ElementCollection
    private List<ItemAvariado> itensAvariados = new ArrayList<>();
    @PositiveOrZero(message = "O retorno não pode ser negativo")
    private int itensRetorno;
    @PositiveOrZero(message = "O bônus não pode ser negativo")
    private int itensBonificacao;
    @PositiveOrZero(message = "Os quilômetros não podem ser negativos")
    private int quilometragem;
    @ElementCollection
    private List<Despesa> despesas = new ArrayList<>();
    private BigDecimal valorFinal;

    public Viagem(LocalDate dataSaida, String rota, int carga, long veiculoId){
        this.dataSaida = dataSaida;
        this.rota = rota;
        this.carga = carga;
        this.veiculoId = veiculoId;
        this.finalizada = false;
    }

    public void finalizar(
            List<ItemVendido> itensVendidos,
            List<ItemAvariado> itensAvariados,
            List<Despesa> despesas,
            int itensRetorno,
            int itensBonificacao,
            int quilometragem,
            BigDecimal valorFinal
    ){
        this.valorFinal = valorFinal;
        this.itensVendidos = itensVendidos;
        this.itensAvariados = itensAvariados;
        this.itensRetorno = itensRetorno;
        this.itensBonificacao = itensBonificacao;
        this.quilometragem = quilometragem;
        this.despesas = despesas;
        this.finalizada = true;
    }


    @Override
    public String toString(){
        return "{id -> " + id +
                "; data -> " + dataSaida +
                "; rota -> " + rota +
                "; carga -> " + carga +
                "; veiculoId -> " + veiculoId +
                "; finalizada -> " + finalizada +
                "; itens vendidos -> " + itensVendidos +
                "; itens itensAvariados -> " + itensAvariados +
                "; itens retorno -> " + itensRetorno +
                "; bonificação -> " + itensBonificacao +
                "; quilometragem -> " + quilometragem +
                "; despesas -> " + despesas +
                "; valor final -> " + valorFinal +
                "}";
    }

}
