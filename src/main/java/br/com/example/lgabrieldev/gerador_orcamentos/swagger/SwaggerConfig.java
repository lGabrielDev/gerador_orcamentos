package br.com.example.lgabrieldev.gerador_orcamentos.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI gerarSwagger(){
        return new OpenAPI()
            .info(
                new Info().title("Gerador de orcamentos").description("Automatiza a criação e envio de orçamentos, tornando o processo rápido e eficiente")
                    .license(
                        new License().name("MIT").url("https://opensource.org/license/mit")
                    )
                    .contact(
                        new Contact().email("thegabrielfreitasbf@yahoo.com.br").name("lGabrielDev")
                    )
                    .version("1.0")
            )
            .servers(
                List.of(
                    new Server().url("http://localhost:8080")
                    .description("")
                )
            );
    }
}
