package com.nocta.eventmanager.catalog.infrastructure.configurations

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfiguration {
    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Catalog API")
                    .version("v1")
                    .description("API para o gerenciamento de catologos de eventos")
                    .license(
                        License().name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0")
                    ).contact(Contact().name("Nocta Solutions").email("contato@noctasolutions.com"))
            )
    }
}