package com.project.msvc_prize.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("eSports Arena Manager API")
                        .version("1.0")
                        .description("Documentación OpenAPI del microservicio de gestión y asignación de premios de eSports Arena Manager"));
    }
}