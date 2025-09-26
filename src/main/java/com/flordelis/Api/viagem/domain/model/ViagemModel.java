package com.flordelis.Api.viagem.domain.model;

import com.flordelis.Api.viagem.application.dto.FinalizarViagemDTO;
import com.flordelis.Api.viagem.application.exception.RetornoBadQuantityException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

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

    public ViagemModel(LocalDate data, String rota, int carga, long veiculoId){
        this.data = data;
        this.rota = rota;
        this.carga = carga;
        this.veiculoId = veiculoId;
        this.finalizada = false;
    }

    public void finalizar(FinalizarViagemDTO dto){
        this.validarCarga(dto);
        this.setPrecos(dto.getPrecos());
        this.setAvariados(dto.getAvariados());
        this.setRetorno(dto.getRetorno());
        this.setBonus(dto.getBonus());
        this.setKms(dto.getKms());
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
                "}";
    }

}
