package com.flordelis.Api.service;

import com.flordelis.Api.dto.CriarViagemDTO;
import com.flordelis.Api.dto.FinalizarViagemDTO;
import com.flordelis.Api.exception.ViagemNotFoundException;
import com.flordelis.Api.model.ViagemModel;
import com.flordelis.Api.repository.ViagemRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public ViagemModel getById(Long id){
        return viagemRepository.findById(id)
                .orElseThrow(ViagemNotFoundException::new);
    }

    //Listar viagens por data
    public List<ViagemModel> findByData(LocalDate data){return viagemRepository.findByData(data);}

    //Listar viagens abertas/finalizadas
    public List<ViagemModel> findByFinalizada(boolean finalizada){return viagemRepository.findByFinalizada(finalizada);}

    //Adicionar Viagem
    public ViagemModel create(ViagemModel viagem){return viagemRepository.save(viagem);}

    //Finalizar Viagem
    public ViagemModel finalizar(Long id, FinalizarViagemDTO dto){
        ViagemModel viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada com id " + id));

        viagem.setValor(dto.getValor());
        viagem.setAvariados(dto.getAvariados());
        viagem.setFinalizada(true);

        return viagemRepository.save(viagem);
    }

    //Deletar Viagem
    public void delete(Long id){
        ViagemModel viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada com id " + id));
        viagemRepository.delete(viagem);}
}
