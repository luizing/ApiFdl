package com.flordelis.Api.exception;

public class ViagemAlreadyFinishedException extends RuntimeException{

    public ViagemAlreadyFinishedException(){
        super("Viagem já está finalizada");
    }

    public ViagemAlreadyFinishedException(String mensagem){
        super(mensagem);
    }
}
