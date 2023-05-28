package com.example.resourceserver.config;

import com.example.resourceserver.jwt.JWTConfiguration;
import com.example.resourceserver.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CloudConfiguration {

    private static final String LOGIN_END = "/cloud/login";
    private static final String CLOUD_END = "/cloud/**";

    private final JWTTokenProvider provider;

    @Autowired
    public CloudConfiguration(JWTTokenProvider provider) {
        this.provider = provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        var providerManager = new DaoAuthenticationProvider();
        providerManager.setUserDetailsService(null);
        providerManager.setPasswordEncoder(null);
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
}
