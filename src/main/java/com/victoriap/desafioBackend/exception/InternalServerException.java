package com.victoriap.desafioBackend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Data
@EqualsAndHashCode(callSuper=false)
public class InternalServerException extends RuntimeException {

    private String statusText;
    private HttpStatus statusCode;
    public String message;



    public InternalServerException(String message) {
    }

    public InternalServerException(HttpStatus statusCode, String message, String statusText) {
        super(message);
        this.statusText = statusText;
        this.statusCode = statusCode;
        this.message = message;
    }
}