package com.flordelis.Api.viagem.application.exception;

public class ValorBadReturnException extends RuntimeException {
    public ValorBadReturnException(){
        super("Valor recebido difere do valor esperado.");
    }

    public ValorBadReturnException(String mensagem){
        super(mensagem);
    }
}
