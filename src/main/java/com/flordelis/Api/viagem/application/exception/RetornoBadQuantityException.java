package com.flordelis.Api.viagem.application.exception;

public class RetornoBadQuantityException extends RuntimeException {
    public RetornoBadQuantityException(){
        super("Carga inicial da viagem difere da soma de quantidade vendida, itensAvariados, itensBonus e retorno.");
    }

    public  RetornoBadQuantityException(String mensagem){
        super(mensagem);
    }
}
