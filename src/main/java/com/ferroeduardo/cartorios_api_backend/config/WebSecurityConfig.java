package com.ferroeduardo.cartorios_api_backend.config;

import com.ferroeduardo.cartorios_api_backend.communication.CommunicationBetweenServicesAuthFilter;
import com.ferroeduardo.cartorios_api_backend.communication.CommunicationBetweenServicesAuthManager;
import com.ferroeduardo.cartorios_api_backend.util.ServicesCommunicationUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ServicesCommunicationUtil servicesCommunicationUtil;

    public WebSecurityConfig(ServicesCommunicationUtil servicesCommunicationUtil) {
        this.servicesCommunicationUtil = servicesCommunicationUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/**")
                    .permitAll()
                .and()
            .csrf()
                .disable()
            .cors()
                .disable();

        /*
            Services Communication
         */

        CommunicationBetweenServicesAuthFilter servicesFilter = new CommunicationBetweenServicesAuthFilter(servicesCommunicationUtil.usernameHeaderName, servicesCommunicationUtil.passwordHeaderName);
        servicesFilter.setAuthenticationManager(new CommunicationBetweenServicesAuthManager(servicesCommunicationUtil.serviceUsername, servicesCommunicationUtil.servicePassword));

        http.authorizeRequests()
                .antMatchers("/api/frontend/**")
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
