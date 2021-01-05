package com.ferroeduardo.cartorios_api_backend.service;

import com.ferroeduardo.cartorios_api_backend.entity.ApiKey;
import com.ferroeduardo.cartorios_api_backend.exception.ApiKeyNotFoundException;
import com.ferroeduardo.cartorios_api_backend.repository.ApiKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    private Logger logger = LoggerFactory.getLogger(ApiKeyService.class);

    private ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Transactional(readOnly = true)
    public ApiKey findByApiKey(String apiKey) throws ApiKeyNotFoundException {
        Optional<ApiKey> apiApiKeyOptional = apiKeyRepository.findByKey(apiKey);
        return apiApiKeyOptional.orElseThrow(() -> new ApiKeyNotFoundException(apiKey));
    }

    @Transactional(readOnly = true)
    public ApiKey findByUserId(Long userId) throws ApiKeyNotFoundException {
        Optional<ApiKey> apiApiKeyOptional = apiKeyRepository.findByUserId(userId);
        return apiApiKeyOptional.orElseThrow(() -> new ApiKeyNotFoundException(userId));
    }

    @Transactional(readOnly = false)
    public String createNewApiKey(Long userId) {
        UUID uuid = UUID.randomUUID();
        String apiKey = uuid.toString();

        try {
            ApiKey apiApiKey = findByUserId(userId);
            apiApiKey.setKey(apiKey);
            apiKeyRepository.save(apiApiKey);
            logger.info(String.format("Api key do userId='%d' já existia e um novo foi criado com sucesso", userId));
        } catch (ApiKeyNotFoundException e) {
            ApiKey apiApiKey = new ApiKey(userId, apiKey);
            apiKeyRepository.save(apiApiKey);
            logger.info(String.format("Api key do userId='%d' não existia antes e um novo criado com sucesso", userId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return apiKey;
    }

    @Transactional(readOnly = true)
    public boolean validApiKey(String apiKey) {
        try {
            ApiKey apiApiKey = findByApiKey(apiKey);
            return apiApiKey.getKey() != null;
        } catch (ApiKeyNotFoundException e) {
            logger.warn(e.getMessage(), e);
            return false;
        }
    }

    @Transactional(readOnly = false)
    public boolean revokeApiKey(Long userId) {
        try {
            ApiKey apiApiKey = findByUserId(userId);
            apiApiKey.setKey(null);
            apiKeyRepository.save(apiApiKey);
            return true;
        } catch (ApiKeyNotFoundException e) {
            logger.warn(e.getMessage(), e);
            return false;
        }
    }

    @Transactional(readOnly = true)
    public boolean exists(Long userId) {
        return apiKeyRepository.existsByUserId(userId);
    }

    @Transactional(readOnly = false)
    public ApiKey createRow(Long userId) {
        if (this.exists(userId)) {
            return apiKeyRepository.findByUserId(userId).orElseThrow(() -> new ApiKeyNotFoundException(userId));
        } else {
            return apiKeyRepository.save(new ApiKey(userId, null));
        }
    }
}
