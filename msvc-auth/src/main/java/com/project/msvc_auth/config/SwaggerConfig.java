package com.project.msvc_auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API eSports Arena Manager")
                        .version("1.0")
                        .description("Documentación OpenAPI del microservicio de autenticación y gestión de acceso de eSports Arena Manager"));
    }
}