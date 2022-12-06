package com.victoriap.desafioBackend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class ResourceNotFoundException extends RuntimeException {
    private String code;
    private HttpStatus status;

    public ResourceNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ResourceNotFoundException(String code) {
        this.code = code;
    }

    public ResourceNotFoundException() {

    }
}