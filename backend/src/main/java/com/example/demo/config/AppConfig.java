package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {

    @Bean
    public OpenAPI langLoopOpenApi() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
        .info(new Info()
                .title("LangLoop API")
                .description("Language Immersion & Flashcard Engine")
                .version("v1"))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .schemaRequirement(securitySchemeName, new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"));
    }
}