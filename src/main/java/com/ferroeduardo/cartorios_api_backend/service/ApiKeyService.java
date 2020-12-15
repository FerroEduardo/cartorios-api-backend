package com.ferroeduardo.cartorios_api_backend.service;

import com.ferroeduardo.cartorios_api_backend.entity.ApiKey;
import com.ferroeduardo.cartorios_api_backend.exception.ApiKeyNotFoundException;
import com.ferroeduardo.cartorios_api_backend.repository.ApiKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    private Logger logger = LoggerFactory.getLogger(ApiKeyService.class);

    private ApiKeyRepository tokenRepository;

    public ApiKeyService(ApiKeyRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public ApiKey findByToken(String token) throws ApiKeyNotFoundException {
        Optional<ApiKey> apiTokenOptional = tokenRepository.findByToken(token);
        return apiTokenOptional.orElseThrow(() -> new ApiKeyNotFoundException(token));
    }

    public ApiKey findByUserId(Long userId) throws ApiKeyNotFoundException {
        Optional<ApiKey> apiTokenOptional = tokenRepository.findByUserId(userId);
        return apiTokenOptional.orElseThrow(() -> new ApiKeyNotFoundException(userId));
    }

    public String createNewToken(Long userId) {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();

        try {
            ApiKey apiToken = findByUserId(userId);
            apiToken.setKey(token);
            tokenRepository.save(apiToken);
            logger.info(String.format("Token do userId='%d' já existia e um novo foi criado com sucesso", userId));
        } catch (ApiKeyNotFoundException e) {
            ApiKey apiToken = new ApiKey(userId, token);
            tokenRepository.save(apiToken);
            logger.info(String.format("Token do userId='%d' não existia antes e um novo criado com sucesso", userId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return token;
    }

    public boolean validToken(String token) {
        try {
            ApiKey apiToken = findByToken(token);
            return apiToken.getKey() != null;
        } catch (ApiKeyNotFoundException e) {
            logger.warn(e.getMessage(), e);
            return false;
        }
    }

    public boolean revokeToken(Long userId) {
        try {
            ApiKey apiToken = findByUserId(userId);
            apiToken.setKey(null);
            tokenRepository.save(apiToken);
            return true;
        } catch (ApiKeyNotFoundException e) {
            logger.warn(e.getMessage(), e);
            return false;
        }
    }

    public boolean exists(Long userId) {
        return tokenRepository.existsByUserId(userId);
    }

    public ApiKey createRow(Long userId) {
        if (this.exists(userId)) {
            return tokenRepository.findByUserId(userId).orElseThrow(() -> new ApiKeyNotFoundException(userId));
        } else {
            return tokenRepository.save(new ApiKey(userId, null));
        }
    }
}
