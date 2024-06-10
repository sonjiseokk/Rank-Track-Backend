package com.ssafy.ranktrack.global.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("RT(Rank Track) API")
                .description("광주 5반(전) 의 백준 데이터를 모아보자");

        return new OpenAPI()
                .info(info);
    }
}
