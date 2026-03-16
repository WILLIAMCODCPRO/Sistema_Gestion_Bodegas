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
                        .title("Documentacion Apis Sistema de gestión de bodegas LogiTrack")
                        .version("1.0.3")
                        .description("Esta API, se construyó para bodegas LogiTrack"));
    }
}
