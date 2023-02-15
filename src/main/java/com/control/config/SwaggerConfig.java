package com.control.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
/*
 * @see http://localhost:8080/swagger-ui/index.html#/
 * 
 * @see
 * https://lemoncode21.medium.com/how-to-add-openapi-and-swagger-in-spring-boot-
 * 3-7c8d4dbc1f6e
 */
public class SwaggerConfig {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI().info(new Info().title("CONTROL - OpenAPI definition").version("1.0.0").description(""));
    }

}