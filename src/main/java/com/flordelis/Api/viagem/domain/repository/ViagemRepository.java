package com.flordelis.Api.viagem.domain.repository;

import com.flordelis.Api.viagem.domain.model.Viagem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ViagemRepository {
    List<Viagem> findAll();
    Optional<Viagem> findById(Long id);
    Viagem save(Viagem viagem);
    void deleteById(Long id);

    List<Viagem> findByFinalizada(boolean finalizada);
    List<Viagem> findByData(LocalDate data);
}