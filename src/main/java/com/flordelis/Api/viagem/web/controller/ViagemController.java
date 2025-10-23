package com.flordelis.Api.viagem.web.controller;
import com.flordelis.Api.viagem.application.dto.ViagemCriacaoRequest;
import com.flordelis.Api.viagem.application.dto.ViagemFinalizacaoRequest;
import com.flordelis.Api.viagem.application.dto.ViagemResponse;
import com.flordelis.Api.viagem.domain.model.Viagem;
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
    public ResponseEntity<List<ViagemResponse>> getAll(){
        log.info("GET /viagem -> buscando todas as viagens");
        List<Viagem> viagens = viagemService.getAll();
        List<ViagemResponse> viagemDTO = viagens.stream()
                        .map(ViagemResponse::fromEntity)
                                .toList();
        log.debug("Resultado da consulta getAll: {}", viagens);
        log.info("GET /viagem -> retorno {} viagens", viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponse> getById(@PathVariable Long id){
        log.info("GET /viagem/{} -> buscando viagem por id {}", id, id);
        Viagem viagem = viagemService.getById(id);
        log.debug("GET /viagem/{} -> detalhes da viagem retornada: {}", id, viagem);
        log.info("GET /viagem/{id} -> retorna viagem {}", viagem.getId());
        return ResponseEntity.ok(ViagemResponse.fromEntity(viagem));
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<ViagemResponse>> getByAbertas(){
        log.info("GET /viagem/abertas -> buscando viagens em aberto");
        List<Viagem> viagens = viagemService.findByFinalizada(false);
        List<ViagemResponse> viagemDTO = viagens.stream()
                .map(ViagemResponse::fromEntity)
                .toList();
        log.debug("Resultado da consulta findByAbertas: {}", viagens);
        log.info("GET /viagem/abertas -> retorno {} viagens", viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping("/finalizadas")
    public ResponseEntity<List<ViagemResponse>> getByFinalizada(){
        log.info("GET /viagem/finalizadas -> buscando viagens finalizadas");
        List<Viagem> viagens = viagemService.findByFinalizada(true);
        List<ViagemResponse> viagemDTO = viagens.stream()
                .map(ViagemResponse::fromEntity)
                .toList();
        log.debug("Resultado da consulta findByFinalizadas: {}", viagens);
        log.info("GET /viagem/finalizadas -> retorno {} viagens", viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping("/data/{date}")
    public ResponseEntity<List<ViagemResponse>> getByDate(@PathVariable("date")LocalDate date){
        log.info("GET /viagem/data/{date} -> buscando viagens pela data {}",date);
        List<Viagem> viagens = viagemService.findByData(date);
        List<ViagemResponse> viagemDTO = viagens.stream()
                .map(ViagemResponse::fromEntity)
                .toList();
        log.debug("Resultado da consulta findByData: {}", viagens);
        log.info("GET /viagem/data/{date} -> retorno {} viagens",viagens.size());
        return ResponseEntity.ok(viagemDTO);
    }

    @PostMapping
    public ResponseEntity<ViagemResponse> create(@Valid @RequestBody ViagemCriacaoRequest dto) {
        log.info("POST /viagem -> criando viagem com dados: {}", dto);
        Viagem novaViagem = viagemService.create(dto.toEntity());
        log.debug("POST /viagem -> detalhes da viagem criada: {}", novaViagem);
        log.info("POST /viagem -> viagem criada com id: {}", novaViagem.getId());
        return new ResponseEntity<>(ViagemResponse.fromEntity(novaViagem),HttpStatus.CREATED);}


    @PatchMapping("/{id}")
    public ResponseEntity<ViagemResponse> finalizar(@PathVariable Long id, @Valid @RequestBody ViagemFinalizacaoRequest dto){
        log.info("PATCH /viagem/{} -> finalizando viagem", id);
        Viagem viagemFinalizada = viagemService.finalizar(id, dto);
        log.debug("PATCH /viagem/{} -> detalhes da viagem finalizada: {}",id, viagemFinalizada);
        log.info("PATCH /viagem/{} -> viagem finalizada com sucesso", id);
        return ResponseEntity.ok(ViagemResponse.fromEntity(viagemFinalizada));
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
