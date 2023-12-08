package com.lina.individuelluppgift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
public class SizeTooLargeException extends RuntimeException{

    public SizeTooLargeException(String message){
        super(message);
    }
}
