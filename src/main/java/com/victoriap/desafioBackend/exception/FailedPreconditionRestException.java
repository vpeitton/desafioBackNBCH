package com.victoriap.desafioBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class FailedPreconditionRestException extends RuntimeException {

    private static final long serialVersionUID = -2140457687610016185L;


    public FailedPreconditionRestException(String message) {
        super(message);
    }


    public FailedPreconditionRestException(Throwable cause) {
        super(cause);
    }


    public FailedPreconditionRestException(String message, Throwable cause) {
        super(message, cause);
    }


    public FailedPreconditionRestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FailedPreconditionRestException() {

    }
}