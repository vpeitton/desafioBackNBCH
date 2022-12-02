package com.victoriap.desafioBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ErrorNoEncontrado extends RuntimeException{

    public ErrorNoEncontrado() {
    }
  
    public ErrorNoEncontrado(String message) {
        super(message);
    }

    public ErrorNoEncontrado(Throwable cause) {
        super(cause);
    }

    public ErrorNoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ErrorNoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
