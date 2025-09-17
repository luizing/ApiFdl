package com.flordelis.Api.repository;

import com.flordelis.Api.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ViagemRepository extends JpaRepository<ViagemModel,Long> {
    List<ViagemModel> findByData(LocalDate data);
    List<ViagemModel> findByFinalizada(boolean finalizada);
}
