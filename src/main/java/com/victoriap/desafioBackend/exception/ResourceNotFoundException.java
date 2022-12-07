package com.victoriap.desafioBackend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceNotFoundException extends NullPointerException {
    public HttpStatus statusCode;
    public String statusText;
    public String message;

    public ResourceNotFoundException(HttpStatus statusCode, String message, String statusText) {
        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.message = message;
    }

    public ResourceNotFoundException(String statusText) {
        this.statusText = statusText;
    }

    public ResourceNotFoundException() {

    }
}
