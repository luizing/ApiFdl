package com.flordelis.Api.viagem.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {
    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private int quantidade;
    @NotNull(message = "O valor é obrigatória")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;
}