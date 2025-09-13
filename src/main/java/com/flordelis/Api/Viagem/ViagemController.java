package com.flordelis.Api.Viagem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagem")
public class ViagemController {


    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService){
        this.viagemService = viagemService;
    }

    @GetMapping
    public List<ViagemModel> getAll(){return viagemService.getAll();}

    @PostMapping
    public ViagemModel create(@RequestBody ViagemModel viagem) {return viagemService.create(viagem);}

}
