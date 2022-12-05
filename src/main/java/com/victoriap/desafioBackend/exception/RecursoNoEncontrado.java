package com.victoriap.desafioBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class RecursoNoEncontrado extends RuntimeException{

    public RecursoNoEncontrado() {
    }
  
    public RecursoNoEncontrado(String message) {
        super(message);
    }

    public RecursoNoEncontrado(Throwable cause) {
        super(cause);
    }

    public RecursoNoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RecursoNoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
