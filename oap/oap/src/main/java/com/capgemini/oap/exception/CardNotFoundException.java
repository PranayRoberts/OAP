package com.capgemini.oap.exception;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(String msg){
        super(msg);
    }
}
