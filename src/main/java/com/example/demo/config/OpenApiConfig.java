package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean(name = "paymelater")
    public OpenAPI bloggingOpenApi() {
        // Available at
        // http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("PayMeLater Application API")
                        .description(
                                "PayMeLater API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3."));
    }
}

