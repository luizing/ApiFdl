package com.flordelis.Api.Viagem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }

    //Listar todas as viagens
    public List<ViagemModel> getAll(){return viagemRepository.findAll();}

    //Adicionar Viagem
    public ViagemModel create(ViagemModel viagem){return viagemRepository.save(viagem);}

    //Deletar Viagem
    public void delete(Long id){viagemRepository.deleteById(id);}
}
