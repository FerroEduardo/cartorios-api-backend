package com.ferroeduardo.cartorios_api_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferroeduardo.cartorios_api_backend.entity.ApiKey;
import com.ferroeduardo.cartorios_api_backend.exception.ApiKeyNotFoundException;
import com.ferroeduardo.cartorios_api_backend.service.ApiKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping(path = "/api/key", produces = "application/json", consumes = "application/json")
public class ApiKeyController {

    Logger logger = LoggerFactory.getLogger(ApiKeyController.class);

    private ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping(path = "/generate")
    public String generateApiKey(@RequestBody Map<String, String> requestMap) throws JsonProcessingException {
        Long userId = Long.parseLong(requestMap.get("userId"));
        String newApiKey = apiKeyService.createNewApiKey(userId);
        Map<String, String> responseMap = new TreeMap<>();
        responseMap.put("api_key", newApiKey);
        responseMap.put("user_id", userId.toString());
        ObjectMapper mapper = new ObjectMapper();
        logger.info(String.format("Novo api key gerado para o usuário de ID:'%d'", userId));
        return mapper.writeValueAsString(responseMap);
    }

    @PostMapping(path = "/get")
    public String getApiKey(@RequestBody Map<String, String> requestMap) throws JsonProcessingException {
        Long userId = Long.parseLong(requestMap.get("userId"));
        ApiKey apiKey;
        try {
            apiKey = apiKeyService.findByUserId(userId);
        } catch (ApiKeyNotFoundException e) {
            apiKey = new ApiKey(userId, null);
        }
        Map<String, String> responseMap = new TreeMap<>();
        responseMap.put("api_key", apiKey.getKey());
        responseMap.put("user_id", userId.toString());
        ObjectMapper mapper = new ObjectMapper();
        logger.info(String.format("Api key requisitado para o usuário de ID:'%d'", userId));
        return mapper.writeValueAsString(responseMap);
    }

    @PostMapping(path = "/revoke")
    public ResponseEntity<?> revokeApiKey(@RequestBody Map<String, String> requestMap) {
        Long userId = Long.parseLong(requestMap.get("userId"));
        if (apiKeyService.revokeApiKey(userId)) {
            logger.info(String.format("Api key revogado para o usuário de ID:'%d' com sucesso", userId));
            return ResponseEntity.ok().build();
        } else {
            logger.warn(String.format("Erro ao revogar o api key do usuário de ID:'%d'", userId));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "/authorize")
    public ResponseEntity<?> createApiKeyRow(@RequestBody Map<String, String> requestMap) {
        Long userId = Long.parseLong(requestMap.get("userId"));
        if (!apiKeyService.exists(userId)) {
            apiKeyService.createRow(userId);
            logger.info(String.format("Dado da tabela para o usuário de ID:'%d' foi criado com sucesso", userId));
        }
        logger.info(String.format("Dado da tabela para o usuário de ID:'%d' foi criado com sucesso ou já existia anteriormente", userId));
        return ResponseEntity.ok().build();
    }

}
