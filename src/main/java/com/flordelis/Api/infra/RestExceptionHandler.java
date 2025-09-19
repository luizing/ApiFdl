package com.flordelis.Api.infra;

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
}
