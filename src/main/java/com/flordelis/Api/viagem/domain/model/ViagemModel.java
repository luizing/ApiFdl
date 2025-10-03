package com.flordelis.Api.viagem.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Setter
@Entity
@Table(name = "tb_viagens")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "A data não pode ser nula")
    private LocalDate data;
    @NotBlank(message = "A rota não pode estar vazia")
    private String rota;
    @PositiveOrZero(message = "A carga não pode ser negativa")
    private int carga;
    @Positive(message = "O id do veiculo deve ser positivo")
    private long veiculoId;

    private boolean finalizada;

    @ElementCollection
    private List<ItemVenda> precos = new ArrayList<>();
    @ElementCollection
    private List<ItemAvariado> avariados = new ArrayList<>();
    @PositiveOrZero(message = "O retorno não pode ser negativo")
    private int retorno;
    @PositiveOrZero(message = "O bônus não pode ser negativo")
    private int bonus;
    @PositiveOrZero(message = "Os quilômetros não podem ser negativos")
    private int kms;
    @ElementCollection
    private List<Despesa> despesas = new ArrayList<>();
    private BigDecimal valorFinal;

    public ViagemModel(LocalDate data, String rota, int carga, long veiculoId){
        this.data = data;
        this.rota = rota;
        this.carga = carga;
        this.veiculoId = veiculoId;
        this.finalizada = false;
    }

    public void finalizar(
            List<ItemVenda> precos,
            List<ItemAvariado> avariados,
            List<Despesa> despesas,
            int retorno,
            int bonus,
            int kms,
            BigDecimal valorFinal
    ){
        this.setValorFinal(valorFinal);
        this.setPrecos(precos);
        this.setAvariados(avariados);
        this.setRetorno(retorno);
        this.setBonus(bonus);
        this.setKms(kms);
        this.setDespesas(despesas);
        this.setFinalizada(true);
    }


    @Override
    public String toString(){
        return "{id -> " + id +
                "; data -> " + data +
                "; rota -> " + rota +
                "; carga -> " + carga +
                "; veiculoId -> " + veiculoId +
                "; finalizada -> " + finalizada +
                "; valor -> " + precos +
                "; avariados -> " + avariados +
                "; retorno -> " + retorno +
                "; bonus -> " + bonus +
                "; kms -> " + kms +
                "; despesas -> " + despesas +
                "; valorFinal -> " + valorFinal +
                "}";
    }

}
