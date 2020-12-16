package com.ferroeduardo.cartorios_api_backend.auth;

import com.ferroeduardo.cartorios_api_backend.entity.ApiKey;
import com.ferroeduardo.cartorios_api_backend.exception.ApiKeyNotFoundException;
import com.ferroeduardo.cartorios_api_backend.service.ApiKeyService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthManager implements AuthenticationManager {

    private ApiKeyService apiKeyService;

    public ApiKeyAuthManager(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKeyRequest = (String) authentication.getPrincipal();
        try {
            ApiKey apiKey = apiKeyService.findByApiKey(apiKeyRequest);
            authentication.setAuthenticated(true);
            return authentication;
        } catch (ApiKeyNotFoundException e) {
            throw new BadCredentialsException("A API KEY não foi encontrada ou não é válida", e);
        }
    }
}
