package com.victoriap.desafioBackend.exception;

import com.victoriap.desafioBackend.model.error.ErrorGenerico;
import com.victoriap.desafioBackend.model.error.ErrorNoEncontrado;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException extends Throwable {

    private static final Logger logger = Logger.getLogger(GlobalHandlerException.class);

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorNoEncontrado> badRequestExceptionHandler(NullPointerException e) {
        ErrorNoEncontrado errorNoEncontrado = ErrorNoEncontrado.builder().codigo("404").mensaje(e.getMessage()).build();
        logger.error("NotFoundException: " + e.getMessage());
        return new ResponseEntity<>(errorNoEncontrado, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorGenerico> runtimeExceptionHandler(RuntimeException e) {
        ErrorGenerico errorGenerico = ErrorGenerico.builder().codigo("500").mensaje(e.getMessage()).build();
        logger.error("InternalServerException: "  + e.getMessage());
        return new ResponseEntity<>(errorGenerico, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
