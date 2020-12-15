package com.ferroeduardo.cartorios_api_backend.exception;

import java.util.Optional;

public class ApiKeyNotFoundException extends RuntimeException {

    private String key;
    private Long userId;

    public ApiKeyNotFoundException(String key) {
        super(String.format("Api Key '%s' não encontrado no banco de dados", key));
        this.key = key;
    }

    public ApiKeyNotFoundException(Long userId) {
        super(String.format("Api Key do usuário de ID:'%d' não encontrado no banco de dados", userId));
        this.userId = userId;
    }

    public ApiKeyNotFoundException(String key, Throwable cause) {
        super(cause);
        this.key = key;
    }

    public ApiKeyNotFoundException(Long userId, Throwable cause) {
        super(cause);
        this.userId = userId;
    }

    public Optional<String> getKey() {
        return Optional.ofNullable(this.key);
    }

    public Optional<Long> getUserId() {
        return Optional.ofNullable(this.userId);
    }
}
