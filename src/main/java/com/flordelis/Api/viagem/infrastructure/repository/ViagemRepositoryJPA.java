package com.flordelis.Api.viagem.infrastructure.repository;

import com.flordelis.Api.viagem.domain.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ViagemRepositoryJPA extends JpaRepository<ViagemModel,Long> {
    List<ViagemModel> findByData(LocalDate data);
    List<ViagemModel> findByFinalizada(boolean finalizada);
}
