package com.example.Shopping_portal.Exceptions;

public class InvalidCardException extends RuntimeException{
    public InvalidCardException(String message){
        super(message);
    }
}
