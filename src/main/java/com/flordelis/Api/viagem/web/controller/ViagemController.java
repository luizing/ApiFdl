package com.flordelis.Api.viagem.web.controller;
import com.flordelis.Api.viagem.application.dto.CriarViagemDTO;
import com.flordelis.Api.viagem.application.dto.FinalizarViagemDTO;
import com.flordelis.Api.viagem.application.dto.RetornarViagemDTO;
import com.flordelis.Api.viagem.domain.model.ViagemModel;
import com.flordelis.Api.viagem.domain.service.ViagemService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/viagem")
@Slf4j
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService){
        this.viagemService = viagemService;
    }

    @GetMapping
    public ResponseEntity<List<RetornarViagemDTO>> getAll(){
        log.info("GET /viagem -> buscando todas as viagens");
        List<ViagemModel> viagens = viagemService.getAll();
        List<RetornarViagemDTO> viagemDTO = viagens.stream()
                        .map(RetornarViagemDTO::fromEntity)
                                .toList();
        log.debug("Resultado da consulta getAll: {}", viagens);
        log.info("GET /viagem -> retorno {} viagens", viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetornarViagemDTO> getById(@PathVariable Long id){
        log.info("GET /viagem/{} -> buscando viagem por id {}", id, id);
        ViagemModel viagem = viagemService.getById(id);
        log.debug("GET /viagem/{} -> detalhes da viagem retornada: {}", id, viagem);
        log.info("GET /viagem/{id} -> retorna viagem {}", viagem.getId());
        return ResponseEntity.ok(RetornarViagemDTO.fromEntity(viagem));
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<RetornarViagemDTO>> getByAbertas(){
        log.info("GET /viagem/abertas -> buscando viagens em aberto");
        List<ViagemModel> viagens = viagemService.findByFinalizada(false);
        List<RetornarViagemDTO> viagemDTO = viagens.stream()
                .map(RetornarViagemDTO::fromEntity)
                .toList();
        log.debug("Resultado da consulta findByAbertas: {}", viagens);
        log.info("GET /viagem/abertas -> retorno {} viagens", viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping("/finalizadas")
    public ResponseEntity<List<RetornarViagemDTO>> getByFinalizada(){
        log.info("GET /viagem/finalizadas -> buscando viagens finalizadas");
        List<ViagemModel> viagens = viagemService.findByFinalizada(true);
        List<RetornarViagemDTO> viagemDTO = viagens.stream()
                .map(RetornarViagemDTO::fromEntity)
                .toList();
        log.debug("Resultado da consulta findByFinalizadas: {}", viagens);
        log.info("GET /viagem/finalizadas -> retorno {} viagens", viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping("/data/{date}")
    public ResponseEntity<List<RetornarViagemDTO>> getByDate(@PathVariable("date")LocalDate date){
        log.info("GET /viagem/data/{date} -> buscando viagens pela data {}",date);
        List<ViagemModel> viagens = viagemService.findByData(date);
        List<RetornarViagemDTO> viagemDTO = viagens.stream()
                .map(RetornarViagemDTO::fromEntity)
                .toList();
        log.debug("Resultado da consulta findByData: {}", viagens);
        log.info("GET /viagem/data/{date} -> retorno {} viagens",viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @PostMapping
    public ResponseEntity<RetornarViagemDTO> create(@Valid @RequestBody CriarViagemDTO dto) {
        log.info("POST /viagem -> criando viagem com dados: {}", dto);
        ViagemModel novaViagem = viagemService.create(dto.toEntity());
        log.debug("POST /viagem -> detalhes da viagem criada: {}", novaViagem);
        log.info("POST /viagem -> viagem criada com id: {}", novaViagem.getId());
        return new ResponseEntity<>(RetornarViagemDTO.fromEntity(novaViagem),HttpStatus.CREATED);}


    @PatchMapping("/{id}")
    public ResponseEntity<RetornarViagemDTO> finalizar(@PathVariable Long id, @Valid @RequestBody FinalizarViagemDTO dto){
        log.info("PATCH /viagem/{} -> finalizando viagem", id);
        ViagemModel viagemFinalizada = viagemService.finalizar(id, dto);
        log.debug("PATCH /viagem/{} -> detalhes da viagem finalizada: {}",id, viagemFinalizada);
        log.info("PATCH /viagem/{} -> viagem finalizada com sucesso", id);
        return ResponseEntity.ok(RetornarViagemDTO.fromEntity(viagemFinalizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.info("DELETE /viagem/{} -> deletar viagem", id);
        log.debug("DELETE /viagem/{} -> detalhes da viagem deletada: {}", id, viagemService.getById(id));
        viagemService.delete(id);
        log.info("DELETE /viagem/{} -> viagem deletada com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
