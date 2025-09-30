package com.flordelis.Api.viagem.domain.model;

import com.flordelis.Api.viagem.application.dto.FinalizarViagemDTO;
import com.flordelis.Api.viagem.application.exception.RetornoBadQuantityException;
import com.flordelis.Api.viagem.application.exception.ValorBadReturnException;
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

    public void finalizar(FinalizarViagemDTO dto){
        this.validarCarga(dto);
        this.validarValor(dto);
        this.setPrecos(dto.getPrecos());
        this.setAvariados(dto.getAvariados());
        this.setRetorno(dto.getRetorno());
        this.setBonus(dto.getBonus());
        this.setKms(dto.getKms());
        this.setDespesas(dto.getDespesas());
        this.setValorFinal(dto.getValorFinal());
        this.setFinalizada(true);
    }

    public void validarCarga(FinalizarViagemDTO dto){
        int totalVendido = dto.getPrecos().stream()
                .mapToInt(ItemVenda::getQuantidade)
                .sum();

        int totalAvariados = dto.getAvariados().stream()
                .mapToInt(ItemAvariado::getQuantidade)
                .sum();

        if ((totalVendido + totalAvariados + dto.getRetorno() + dto.getBonus()) != carga){throw new RetornoBadQuantityException();}
    }

    public void validarValor(FinalizarViagemDTO dto){
        BigDecimal totalDespesas = dto.getDespesas().stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalVendas = dto.getPrecos().stream()
                .map(item -> item.getValor().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalVendas.subtract(totalDespesas).compareTo(dto.getValorFinal()) != 0){throw new ValorBadReturnException();}
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
                "}";
    }

}
