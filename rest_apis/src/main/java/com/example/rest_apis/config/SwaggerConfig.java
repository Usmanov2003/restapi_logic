package com.example.rest_apis.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {


        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("Blog Application Beckend API")
                        .description("This is blog application project api and Developed by Md. Talal Wasim")
                        .version("1.0")
                        .contact(new io.swagger.v3.oas.models.info.Contact().name("Md. Talal Wasim").email("md.talalwasim@gmail.com").url("https://mdtalalwasim.github.io/"))
                        .license(new io.swagger.v3.oas.models.info.License().name("Licence"))
                ).externalDocs(new ExternalDocumentation().url("https://mdtalalwasim.github.io/").description("This is external url"));
    }
}
