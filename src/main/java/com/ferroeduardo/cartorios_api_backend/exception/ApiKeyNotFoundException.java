package com.ferroeduardo.cartorios_api_backend.exception;

public class ApiKeyNotFoundException extends RuntimeException {

    private final String key;

    public ApiKeyNotFoundException(String key) {
        super(String.format("Api Key '%s' não encontrado no banco de dados", key));
        this.key = key;
    }

    public ApiKeyNotFoundException(String key, Throwable cause) {
        super(cause);
        this.key = key;
    }
}
