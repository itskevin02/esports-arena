package com.project.msvc_sanction.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info()
                    .title("eSports Arena Manager API")
                    .version("1.0")
                    .description("Documentación OpenAPI del microservicio de gestión de sanciones de eSports Arena Manager")
            )
    }
}