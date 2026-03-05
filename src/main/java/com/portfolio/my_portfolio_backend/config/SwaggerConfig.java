package com.portfolio.my_portfolio_backend.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Portfolio Web")
                        .version("1.0")
                        .description("Documentación interactiva del Backend de mi portfolio. Construido con Spring Boot y PostgreSQL.")
                        .contact(new Contact()
                                .name("Jorge Ortiz")
                                .url("https://jortiz.dev")));
    }
}