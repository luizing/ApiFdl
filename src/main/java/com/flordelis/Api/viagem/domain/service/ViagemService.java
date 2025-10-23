package com.flordelis.Api.viagem.domain.service;

import com.flordelis.Api.viagem.application.dto.ViagemFinalizacaoRequest;
import com.flordelis.Api.viagem.application.exception.RetornoBadQuantityException;
import com.flordelis.Api.viagem.application.exception.ViagemAlreadyFinishedException;
import com.flordelis.Api.viagem.application.exception.ViagemNotFoundException;
import com.flordelis.Api.viagem.domain.model.Viagem;
import com.flordelis.Api.viagem.domain.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository){
        this.viagemRepository = viagemRepository;
    }

    public List<Viagem> getAll(){return viagemRepository.findAll();}

    public Viagem getById(Long id){
        return viagemRepository.findById(id)
                .orElseThrow(ViagemNotFoundException::new);
    }

    public List<Viagem> findByData(LocalDate data){return viagemRepository.findByData(data);}

    public List<Viagem> findByFinalizada(boolean finalizada){return viagemRepository.findByFinalizada(finalizada);}

    public Viagem create(Viagem viagem){return viagemRepository.save(viagem);}

    public Viagem finalizar(Long id, ViagemFinalizacaoRequest dto){
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ViagemNotFoundException("Viagem não encontrada com id " + id));
        if (viagem.isFinalizada()) {throw new ViagemAlreadyFinishedException();}
        if (dto.calcularQtdAvariada() + dto.calcularQtdVendida() + dto.itensRetorno() + dto.itensBonus() != viagem.getCarga()){throw new RetornoBadQuantityException();}
        viagem.finalizar(
                dto.itensVendidos(),
                dto.itensAvariados(),
                dto.despesas(),
                dto.itensRetorno(),
                dto.itensBonus(),
                dto.quilometragem(),
                dto.calcularValorFinal()
        );
        return viagemRepository.save(viagem);
    }

    public void delete(Long id){
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ViagemNotFoundException("Viagem não encontrada com id " + id));
        viagemRepository.deleteById(id);}
}