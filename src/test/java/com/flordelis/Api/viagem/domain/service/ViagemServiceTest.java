package com.flordelis.Api.viagem.domain.service;

import com.flordelis.Api.viagem.application.dto.FinalizarViagemDTO;
import com.flordelis.Api.viagem.application.exception.RetornoBadQuantityException;
import com.flordelis.Api.viagem.application.exception.ViagemAlreadyFinishedException;
import com.flordelis.Api.viagem.application.exception.ViagemNotFoundException;
import com.flordelis.Api.viagem.domain.model.ViagemModel;
import com.flordelis.Api.viagem.domain.repository.ViagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViagemServiceTest {

    @Mock
    private ViagemRepository viagemRepository;

    @InjectMocks
    private ViagemService viagemService;

    private ViagemModel createViagem(){
        return mock(ViagemModel.class);
    }

    private FinalizarViagemDTO createDTO(){
        return mock(FinalizarViagemDTO.class);
    }


    @Test
    @DisplayName("Viagem é finalizada com sucesso quando todas as condições são supridas")
    void finalizarCase1() {
        Long id = 1L;
        int cargaTotal = 100;
        FinalizarViagemDTO dto = createDTO();
        ViagemModel viagemNaoFinalizada = createViagem();

        when(viagemNaoFinalizada.getCarga()).thenReturn(cargaTotal);
        when(viagemNaoFinalizada.isFinalizada()).thenReturn(false);

        when(dto.calcularQtdTotalGarrafoes()).thenReturn(cargaTotal);

        when(viagemRepository.findById(id)).thenReturn(Optional.of(viagemNaoFinalizada));
        when(viagemRepository.save(any(ViagemModel.class))).thenReturn(viagemNaoFinalizada);

        ViagemModel resultado = viagemService.finalizar(id, dto);

        assertNotNull(resultado, "O resultado não deve ser nulo.");
        verify(viagemRepository, times(1)).save(viagemNaoFinalizada);
    }

    @Test
    @DisplayName("Viagem falha na finalização pois não foi encontrada")
    void finalizarCase2() {
        Long idInexistente = 99L;
        FinalizarViagemDTO dto = createDTO();

        when(viagemRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(ViagemNotFoundException.class,
                () -> viagemService.finalizar(idInexistente, dto),
                "Deve lançar ViagemNotFoundException.");

        verify(viagemRepository, never()).save(any());
    }

    @Test
    @DisplayName("Viagem falha na finalização pois já está finalizada")
    void finalizarCase3() {
        Long id = 1L;
        FinalizarViagemDTO dto = createDTO();
        ViagemModel viagemFinalizada = createViagem();

        when(viagemFinalizada.isFinalizada()).thenReturn(true);

        when(viagemRepository.findById(id)).thenReturn(Optional.of(viagemFinalizada));

        assertThrows(ViagemAlreadyFinishedException.class,
                () -> viagemService.finalizar(id, dto),
                "Deve lançar ViagemAlreadyFinishedException.");

        verify(viagemRepository, never()).save(any());
    }

    @Test
    @DisplayName("Viagem falha na finalização pois possui uma divergencia na quantidade que saiu e que voltou")
    void finalizarCase4() {
        Long id = 1L;
        int cargaTotal = 100;
        int cargaDto = 101;
        FinalizarViagemDTO dtoBadQuantity = createDTO();
        ViagemModel viagemNaoFinalizada = createViagem();

        when(viagemNaoFinalizada.getCarga()).thenReturn(cargaTotal);
        when(viagemNaoFinalizada.isFinalizada()).thenReturn(false);
        when(dtoBadQuantity.calcularQtdTotalGarrafoes()).thenReturn(cargaDto);

        when(viagemRepository.findById(id)).thenReturn(Optional.of(viagemNaoFinalizada));

        assertThrows(RetornoBadQuantityException.class,
                () -> viagemService.finalizar(id, dtoBadQuantity),
                "Deve lançar RetornoBadQuantityException.");

        verify(viagemRepository, never()).save(any());
    }

}
