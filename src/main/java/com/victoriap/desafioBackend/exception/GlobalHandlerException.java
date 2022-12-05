package com.victoriap.desafioBackend.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalHandlerException extends Throwable {
    private static final Logger logger = Logger.getLogger(GlobalHandlerException.class);
}
