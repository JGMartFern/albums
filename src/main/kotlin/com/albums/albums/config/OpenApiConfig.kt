package com.albums.albums.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("API for managing Albums and Photos from JSONPlaceholder")
                    .version("1.0")
                    .description("This application is made using Kotlin and Spring Boot with OpenAPI documentation.")
                    .contact(
                        Contact()
                            .name("Juan Gabriel Martínez Fernández")
                            .email("jgmartfern@gmail.com")
                            .url("https://www.linkedin.com/in/juan-gabriel-martinez-fernandez-junior-web-developer/")
                    )
            )
    }
}
