package com.flordelis.Api.exception;

public class ViagemNotFoundException extends RuntimeException{

    public ViagemNotFoundException(){
        super("Viagem não encontrada");
    }

    public ViagemNotFoundException(String mensagem){
        super(mensagem);
    }
}
