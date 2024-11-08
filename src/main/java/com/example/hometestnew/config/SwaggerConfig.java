package com.example.hometestnew.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://hometestjavaprogrammer-production.up.railway.app"))
                .info(new Info().title("Home Test API"))
                .components(new Components().addSecuritySchemes("JavaInUseSecurityScheme",
                        new SecurityScheme()
                                .name("JavaInUseSecurityScheme")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"));
    }
}

