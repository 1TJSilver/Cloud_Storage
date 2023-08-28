package com.example.resourceserver.config;

import com.example.resourceserver.jwt.JWTConfiguration;
import com.example.resourceserver.jwt.JWTTokenProvider;
import com.example.resourceserver.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ComponentScan
public class CloudConfiguration {

    private static final String LOGIN_END = "/cloud/login";
    private static final String CLOUD_END = "/cloud/**";

    private final JWTTokenProvider provider;
    private UserDetailsService detailsService;

    private AuthorizationService authService;

    @Autowired
    public CloudConfiguration(JWTTokenProvider provider) {
        this.provider = provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        var providerManager = new DaoAuthenticationProvider();
        providerManager.setUserDetailsService(null);
        providerManager.setPasswordEncoder(new BCryptPasswordEncoder());
        return new ProviderManager(providerManager);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(LOGIN_END).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfiguration(provider))
                .and()
                .build();
    }

    @Bean
    public JWTTokenProvider provider(){
        return new JWTTokenProvider(detailsService, authService);
    }

    @Autowired
    public void setAuthService(AuthorizationService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setDetailsService(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }
}
