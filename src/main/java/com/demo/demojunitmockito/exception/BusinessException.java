package com.demo.demojunitmockito.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private String message;

    public BusinessException(){

    }

    public BusinessException(String message){
        this.message = message;
    }

}
