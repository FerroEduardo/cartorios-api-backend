package com.ferroeduardo.cartorios_api_backend.config;

import com.ferroeduardo.cartorios_api_backend.auth.ApiKeyAuthFilter;
import com.ferroeduardo.cartorios_api_backend.auth.ApiKeyAuthManager;
import com.ferroeduardo.cartorios_api_backend.communication.CommunicationBetweenServicesAuthFilter;
import com.ferroeduardo.cartorios_api_backend.communication.CommunicationBetweenServicesAuthManager;
import com.ferroeduardo.cartorios_api_backend.service.ApiKeyService;
import com.ferroeduardo.cartorios_api_backend.util.ServicesCommunicationUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_KEY_AUTH_HEADER_NAME = "api_key";

    private ApiKeyService apiKeyService;

    private final ServicesCommunicationUtil servicesCommunicationUtil;

    public WebSecurityConfig(ApiKeyService apiKeyService, ServicesCommunicationUtil servicesCommunicationUtil) {
        this.apiKeyService = apiKeyService;
        this.servicesCommunicationUtil = servicesCommunicationUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
            Api Key
         */

        ApiKeyAuthFilter apiKeyFilter = new ApiKeyAuthFilter(API_KEY_AUTH_HEADER_NAME);
        apiKeyFilter.setAuthenticationManager(new ApiKeyAuthManager(apiKeyService));

        http.authorizeRequests()
                .antMatchers("/api/cartorios/**")
                    .authenticated()
                .and()
            .csrf()
                .disable()
            .cors()
                .and()
            .addFilter(apiKeyFilter)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /*
            Services Communication
         */

        CommunicationBetweenServicesAuthFilter servicesFilter = new CommunicationBetweenServicesAuthFilter(servicesCommunicationUtil.usernameHeaderName, servicesCommunicationUtil.passwordHeaderName);
        servicesFilter.setAuthenticationManager(new CommunicationBetweenServicesAuthManager(servicesCommunicationUtil.serviceUsername, servicesCommunicationUtil.servicePassword));

        http.authorizeRequests()
                .antMatchers("/api/frontend/**", "/api/key/**")
                    .authenticated()
                .and()
            .csrf()
                .disable()
            .cors()
                .and()
            .addFilter(servicesFilter)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
