package com.flordelis.Api.viagem.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Despesa {
    @NotBlank(message = "A finalidade da despesa deve ser informado.")
    private String finalidade;
    @Positive(message = "O valor da despesa deve ser positivo.")
    @NotNull(message = "O valor da despesa não pode ser nulo.")
    private BigDecimal valor;
}
