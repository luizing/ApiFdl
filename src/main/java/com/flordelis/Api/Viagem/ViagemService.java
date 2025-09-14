package com.flordelis.Api.Viagem;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }

    //Listar todas as viagens
    public List<ViagemModel> getAll(){return viagemRepository.findAll();}

    //Listar viagem por id
    public Optional<ViagemModel> getById(Long id){return viagemRepository.findById(id);}

    //Listar viagens por data
    public List<ViagemModel> findByData(LocalDate data){return viagemRepository.findByData(data);}

    //Adicionar Viagem
    public ViagemModel create(ViagemModel viagem){return viagemRepository.save(viagem);}

    //Deletar Viagem
    public void delete(Long id){viagemRepository.deleteById(id);}
}
