package com.ferroeduardo.cartorios_api_backend.communication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CommunicationBetweenServicesAuthManager implements AuthenticationManager {

    private String servicesUsername;
    private String servicesPassword;

    public CommunicationBetweenServicesAuthManager(String servicesUsername, String servicesPassword) {
        this.servicesUsername = servicesUsername;
        this.servicesPassword = servicesPassword;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String requestUsername = (String) authentication.getPrincipal();
        String requestPassword = (String) authentication.getCredentials();

        if (isAuthenticated(requestUsername, requestPassword)) {
            authentication.setAuthenticated(true);
        } else {
            throw new BadCredentialsException("Usuário ou senha incorretos, não foi possivel fazer a comunicação entre os serviços");
        }

        return authentication;
    }

    private boolean isAuthenticated(String requestUsername, String requestPassword) {
        return servicesUsername.equals(requestUsername) && servicesPassword.equals(requestPassword);
    }
}
