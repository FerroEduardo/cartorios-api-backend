package com.ferroeduardo.cartorios_api_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ApiCustomException extends RuntimeException {

    private final Logger logger;

    private final HttpStatus httpStatus;

    public ApiCustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.logger = LoggerFactory.getLogger(ApiCustomException.class);
        logger.warn(message);
    }

    public ApiCustomException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.logger = LoggerFactory.getLogger(ApiCustomException.class);
        logger.warn(message, cause);
    }

    public ApiCustomException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
        this.logger = LoggerFactory.getLogger(ApiCustomException.class);
        logger.warn(cause.getMessage(), cause);
    }

    public ApiCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
        this.logger = LoggerFactory.getLogger(ApiCustomException.class);
        logger.warn(cause.getMessage(), cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
