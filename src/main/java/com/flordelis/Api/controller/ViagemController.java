package com.flordelis.Api.controller;
import com.flordelis.Api.model.ViagemModel;
import com.flordelis.Api.service.ViagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@RestController
@RequestMapping("/viagem")
public class ViagemController {


    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService){
        this.viagemService = viagemService;
    }

    @GetMapping
    public ResponseEntity<List<ViagemModel>> getAll(){
        List<ViagemModel> viagens = viagemService.getAll();
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> getById(@PathVariable("id")Long id){
        Optional<ViagemModel> viagem = viagemService.getById(id);
        return viagem.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/data/{date}")
    public ResponseEntity<List<ViagemModel>> getByDate(@PathVariable("date")LocalDate date){
        List<ViagemModel> viagens = viagemService.findByData(date);
        return ResponseEntity.ok(viagens);
    }

    @PostMapping
    public ResponseEntity<ViagemModel> create(@Valid @RequestBody ViagemModel viagem) {
        ViagemModel novaViagem = viagemService.create(viagem);
        return new ResponseEntity<>(novaViagem,HttpStatus.CREATED);}
}
