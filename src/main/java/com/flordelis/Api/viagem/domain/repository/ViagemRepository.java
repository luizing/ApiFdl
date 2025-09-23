package com.flordelis.Api.viagem.domain.repository;

import com.flordelis.Api.viagem.domain.model.ViagemModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ViagemRepository {
    List<ViagemModel> findAll();
    Optional<ViagemModel> findById(Long id);
    ViagemModel save(ViagemModel viagem);
    void deleteById(Long id);

    List<ViagemModel> findByFinalizada(boolean finalizada);
    List<ViagemModel> findByData(LocalDate data);
}