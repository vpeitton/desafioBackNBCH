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

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado contenido")
    public ResponseEntity<ErrorNoEncontrado> errorNotFound(ResourceNotFoundException e) {
        logger.error("RecursoNoEncontrado: " + e.getMessage());
        ErrorNoEncontrado error = ErrorNoEncontrado.builder().codigo(e.getCode()).mensaje(e.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error interno del servidor")
    public ResponseEntity<ErrorGenerico> handleIllegalArgumentException(InternalServerException e) {
        logger.error("Error interno del servidor: " + e.getMessage());
        ErrorGenerico error = ErrorGenerico.builder().codigo(e.getCode()).mensaje(e.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
