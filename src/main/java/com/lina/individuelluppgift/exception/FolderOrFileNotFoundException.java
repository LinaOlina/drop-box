package com.lina.individuelluppgift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FolderOrFileNotFoundException extends RuntimeException{
    public FolderOrFileNotFoundException(String message){
        super(message);
    }

}
