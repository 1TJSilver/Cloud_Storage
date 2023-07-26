package com.example.resourceserver.config;
import com.example.resourceserver.jwt.JWTUserDetailsService;
import com.example.resourceserver.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer {

    AuthorizationService service;

    @Bean
    public UserDetailsService userDetailsService(){
        return new JWTUserDetailsService(service);
    }

    @Autowired
    public WebConfig(AuthorizationService service){
        this.service = service;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("*");
    }
}
