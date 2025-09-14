package com.flordelis.Api.Viagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ViagemRepository extends JpaRepository<ViagemModel,Long> {
    List<ViagemModel> findByData(LocalDate data);
}
