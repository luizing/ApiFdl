package com.flordelis.Api.infra;

import com.flordelis.Api.exception.ViagemAlreadyFinishedException;
import com.flordelis.Api.exception.ViagemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Viagem não encontrada (404)
    @ExceptionHandler(ViagemNotFoundException.class)
    private ResponseEntity<RestErrorMessage> viagemNotFoundHandler(ViagemNotFoundException exception){
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    // Viagem já foi finalizada (400)
    @ExceptionHandler(ViagemAlreadyFinishedException.class)
    private ResponseEntity<RestErrorMessage> ViagemAlreadyFinishedHandler(ViagemAlreadyFinishedException exception){
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    // Captura qualquer exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleGenericException(Exception ex) {

        // Todo: Implementar logger que exibe no servidor o erro expecífico.

        RestErrorMessage resposta = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}
