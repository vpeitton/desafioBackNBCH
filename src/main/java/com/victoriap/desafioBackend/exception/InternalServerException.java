package com.victoriap.desafioBackend.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Data
public class InternalServerException extends RuntimeException {

    private String code;
    private HttpStatus status;

    public InternalServerException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public InternalServerException(String message) {
    }
}