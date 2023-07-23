package com.example.Shopping_portal.Exceptions;

public class CustomerNotFoundException extends  RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
