package com.victoriap.desafioBackend.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("API Rest desafio Backend")
                        .description("API Rest para desafio Backend NBCH")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Victoria Peitton")
                                .url("https://github.com/vpeitton")
                                .email("victoriap@gpl.com.ar"))
                        .license(new License().name("Victoria Peitton")
                                .url("https://github.com/vpeitton/desafioBackNBCH")));
    }
}
