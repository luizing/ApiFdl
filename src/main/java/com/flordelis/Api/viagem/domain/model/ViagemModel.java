package com.flordelis.Api.viagem.domain.model;

import com.flordelis.Api.viagem.application.dto.FinalizarViagemDTO;
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

    // Atributos de criação da viagem
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "A data não pode ser nula")
    private LocalDate data;
    @NotBlank(message = "A rota não pode estar vazia")
    private String rota;
    @NotNull(message = "A carga não pode ser nula")
    private int carga;
    @Positive(message = "O id do veiculo deve ser positivo")
    private long veiculoId;

    /*@ElementCollection
    @CollectionTable(name = "viagem_funcionarios",
            joinColumns = @JoinColumn(name = "viagem_id"))
    @NotBlank(message = "A lista de funcionários não pode ser nula")
    private List<Long> funcionariosId;
    */

    private boolean finalizada = false;

    // Atributos de finalização de viagem
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

    // Contrutor de criação de viagem
    public ViagemModel(LocalDate data, String rota, int carga, long veiculoId){
        this.data = data;
        this.rota = rota;
        this.carga = carga;
        this.veiculoId = veiculoId;
    }

    public void finalizar(FinalizarViagemDTO dto){
        this.setPrecos(dto.getPrecos());
        this.setAvariados(dto.getAvariados());
        this.setRetorno(dto.getRetorno());
        this.setBonus(dto.getBonus());
        this.setKms(dto.getKms());
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
                "}";
    }

}
