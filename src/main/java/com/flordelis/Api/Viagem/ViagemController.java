package com.flordelis.Api.Viagem;
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
    public List<ViagemModel> getAll(){return viagemService.getAll();}

    @GetMapping("/{id}")
    public Optional<ViagemModel> getById(@PathVariable("id")Long id){return viagemService.getById(id);}

    @GetMapping("/data/{date}")
    public List<ViagemModel> getByDate(@PathVariable("date")LocalDate date){return viagemService.findByData(date);}

    @PostMapping
    public ViagemModel create(@RequestBody ViagemModel viagem) {return viagemService.create(viagem);}

}
