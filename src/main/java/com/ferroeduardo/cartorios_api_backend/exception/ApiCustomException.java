package com.ferroeduardo.cartorios_api_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiCustomException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(ApiCustomException.class);

    public ApiCustomException(String message) {
        super(message);
        logger.warn(message);
    }

    public ApiCustomException(String message, Throwable cause) {
        super(message, cause);
        logger.warn(message, cause);
    }

    public ApiCustomException(Throwable cause) {
        super(cause);
        logger.warn(cause.getMessage(), cause);
    }

    public ApiCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        logger.warn(cause.getMessage(), cause);
    }
}
