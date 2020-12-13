package com.ferroeduardo.cartorios_api_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartorioNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(CartorioNotFoundException.class);

    public CartorioNotFoundException() {
        super("Nenhum cartório foi encontrado");
        logger.warn("Nenhum cartório foi encontrado");
    }

    public CartorioNotFoundException(Long id) {
        super("Cartório de ID:" + id + " não foi encontrado");
        logger.warn("Cartório de ID:" + id + " não foi encontrado");
    }

    public CartorioNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }

    public CartorioNotFoundException(String message, Throwable cause) {
        super(message, cause);
        logger.warn(message, cause);
    }

    public CartorioNotFoundException(Throwable cause) {
        super(cause);
        logger.warn(cause.getMessage(), cause);
    }

    public CartorioNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        logger.warn(cause.getMessage(), cause);
    }
}
