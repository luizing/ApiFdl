package com.flordelis.Api.viagem.infrastructure.repository;

import com.flordelis.Api.viagem.domain.model.ViagemModel;
import com.flordelis.Api.viagem.domain.repository.ViagemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViagemRepositoryJPA extends ViagemRepository, JpaRepository<ViagemModel,Long> {

    @Override
    List<ViagemModel> findByData(LocalDate data);

    @Override
    List<ViagemModel> findByFinalizada(boolean finalizada);
}
