package com.s1.gestion_bodegas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiCofig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("API DOCUMENTADA DE CAMPUSLANDS CON PRODUCTOS Y VENTAS")
                        .version("1.0.3")
                        .description("Esta API, se construyó para el proyecto de springboot"));
    }
}
