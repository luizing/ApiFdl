package com.flordelis.Api.viagem.domain.service;

import com.flordelis.Api.viagem.application.dto.FinalizarViagemDTO;
import com.flordelis.Api.viagem.application.exception.RetornoBadQuantityException;
import com.flordelis.Api.viagem.application.exception.ViagemAlreadyFinishedException;
import com.flordelis.Api.viagem.application.exception.ViagemNotFoundException;
import com.flordelis.Api.viagem.domain.model.ViagemModel;
import com.flordelis.Api.viagem.domain.repository.ViagemRepository;
import com.flordelis.Api.viagem.infrastructure.repository.ViagemRepositoryJPA;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }

    public List<ViagemModel> getAll(){return viagemRepository.findAll();}

    public ViagemModel getById(Long id){
        return viagemRepository.findById(id)
                .orElseThrow(ViagemNotFoundException::new);
    }

    public List<ViagemModel> findByData(LocalDate data){return viagemRepository.findByData(data);}

    public List<ViagemModel> findByFinalizada(boolean finalizada){return viagemRepository.findByFinalizada(finalizada);}

    public ViagemModel create(ViagemModel viagem){return viagemRepository.save(viagem);}

    public ViagemModel finalizar(Long id, FinalizarViagemDTO dto){
        ViagemModel viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ViagemNotFoundException("Viagem não encontrada com id " + id));
        if (viagem.isFinalizada()) {throw new ViagemAlreadyFinishedException();}
        viagem.finalizar(dto);
        return viagemRepository.save(viagem);
    }

    public void delete(Long id){
        ViagemModel viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ViagemNotFoundException("Viagem não encontrada com id " + id));
        viagemRepository.deleteById(id);}
}