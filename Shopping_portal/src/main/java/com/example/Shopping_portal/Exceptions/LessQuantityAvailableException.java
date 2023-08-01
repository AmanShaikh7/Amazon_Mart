package com.example.Shopping_portal.Exceptions;

public class LessQuantityAvailableException extends  RuntimeException{
    public LessQuantityAvailableException(String message){
        super(message);
    }
}
