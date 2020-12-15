package com.ferroeduardo.cartorios_api_backend.communication;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class CommunicationBetweenServicesAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String usernameHeaderName;
    private final String passwordHeaderName;

    public CommunicationBetweenServicesAuthFilter(String usernameHeaderName, String passwordHeaderName) {
        this.usernameHeaderName = usernameHeaderName;
        this.passwordHeaderName = passwordHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(usernameHeaderName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return request.getHeader(passwordHeaderName);
    }
}
