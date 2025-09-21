package com.flordelis.Api.controller;
import com.flordelis.Api.dto.CriarViagemDTO;
import com.flordelis.Api.dto.FinalizarViagemDTO;
import com.flordelis.Api.model.ViagemModel;
import com.flordelis.Api.service.ViagemService;
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
    public ResponseEntity<List<ViagemModel>> getAll(){
        log.info("GET /viagem -> buscando todas as viagens");
        List<ViagemModel> viagens = viagemService.getAll();
        log.debug("Resultado da consulta getAll: {}", viagens);
        log.info("GET /viagem -> retorno {} viagens",viagens.size());
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> getById(@PathVariable Long id){
        log.info("GET /viagem/{} -> buscando viagem por id {}",id,id);
        ViagemModel viagem = viagemService.getById(id);
        log.info("GET /viagem/{id} -> retorna viagem {}",viagem.getId());
        return ResponseEntity.ok(viagem);
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<ViagemModel>> getByAbertas(){
        log.info("GET /viagem/abertas -> buscando viagens em aberto");
        List<ViagemModel> viagens = viagemService.findByFinalizada(false);
        log.debug("Resultado da consulta findByAbertas: {}", viagens);
        log.info("GET /viagem/abertas -> retorno {} viagens",viagens.size());
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/finalizadas")
    public ResponseEntity<List<ViagemModel>> getByFinalizada(){
        log.info("GET /viagem/finalizadas -> buscando viagens finalizadas");
        List<ViagemModel> viagens = viagemService.findByFinalizada(true);
        log.debug("Resultado da consulta findByFinalizadas: {}", viagens);
        log.info("GET /viagem/finalizadas -> retorno {} viagens",viagens.size());
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/data/{date}")
    public ResponseEntity<List<ViagemModel>> getByDate(@PathVariable("date")LocalDate date){
        log.info("GET /viagem/data/{date} -> buscando viagens pela data {}",date);
        List<ViagemModel> viagens = viagemService.findByData(date);
        log.debug("Resultado da consulta findByData: {}", viagens);
        log.info("GET /viagem/data/{date} -> retorno {} viagens",viagens.size());
        return ResponseEntity.ok(viagens);
    }

    @PostMapping
    public ResponseEntity<ViagemModel> create(@Valid @RequestBody CriarViagemDTO dto) {
        log.info("POST /viagem -> criando viagem com dados: {}", dto);
        ViagemModel novaViagem = viagemService.create(dto.converter());
        log.debug("POST /viagem -> detalhes da viagem criada: {}", novaViagem);
        log.info("POST /viagem -> viagem criada com id: {}", novaViagem.getId());
        return new ResponseEntity<>(novaViagem,HttpStatus.CREATED);}


    @PatchMapping("/{id}")
    public ResponseEntity<ViagemModel> finalizar(@PathVariable Long id, @RequestBody FinalizarViagemDTO dto){
        log.info("PATCH /viagem/{} -> finalizando viagem", id);
        ViagemModel viagemFinalizada = viagemService.finalizar(id, dto);
        log.debug("PATCH /viagem/{} -> detalhes da viagem finalizada: {}",id, viagemFinalizada);
        log.info("PATCH /viagem/{} -> viagem finalizada com sucesso", id);
        return ResponseEntity.ok(viagemFinalizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.info("DELETE /viagem/{} -> deletar viagem", id);
        viagemService.delete(id);
        log.info("DELETE /viagem/{} -> viagem deletada com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
