package com.flordelis.Api.shared.infra;

import com.flordelis.Api.viagem.application.exception.RetornoBadQuantityException;
import com.flordelis.Api.viagem.application.exception.ValorBadReturnException;
import com.flordelis.Api.viagem.application.exception.ViagemAlreadyFinishedException;
import com.flordelis.Api.viagem.application.exception.ViagemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(ViagemNotFoundException.class)
    private ResponseEntity<RestErrorMessage> viagemNotFoundHandler(ViagemNotFoundException exception){
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(ViagemAlreadyFinishedException.class)
    private ResponseEntity<RestErrorMessage> ViagemAlreadyFinishedHandler(ViagemAlreadyFinishedException exception){
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidType(HttpMessageNotReadableException exception) {
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(RetornoBadQuantityException.class)
    public ResponseEntity<RestErrorMessage> RetornoBadQuantityHandle(RetornoBadQuantityException exception) {
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(ValorBadReturnException.class)
    public ResponseEntity<RestErrorMessage> ValorBadReturnException(ValorBadReturnException exception){
        RestErrorMessage resposta = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleGenericException(Exception ex) {
        RestErrorMessage resposta = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}
