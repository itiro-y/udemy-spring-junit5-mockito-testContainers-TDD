package com.br.ayrton.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Tests Swagger UI OpenAPI")
                        .version("1.0")
                        .description("Spring Tests Swagger OpenAPI")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0")
                        .url("")));
    }
}
