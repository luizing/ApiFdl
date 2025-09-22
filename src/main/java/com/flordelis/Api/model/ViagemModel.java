package com.flordelis.Api.model;

import com.flordelis.Api.dto.FinalizarViagemDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    /*
            Campos a serem adicionados após modelagem das outras classes.
    @NotNull(message = "O veiculo não pode ser nulo")
    private Veiculo veiculo;
    @NotBlank(message = "A lista de funcionários não pode ser nula")
    private List<FuncionarioModel> funcionarios;
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
    public ViagemModel(LocalDate data, String rota, int carga){
        this.data = data;
        this.rota = rota;
        this.carga = carga;
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
                "; finalizada -> " + finalizada +
                "; valor -> " + precos +
                "; avariados -> " + avariados +
                "}";
    }

}
