package br.com.samuel.manutencao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Chamados de Manutenção", version = "v1"))
public class ManutencaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManutencaoApplication.class, args);
    }
}
