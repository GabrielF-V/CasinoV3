package cl.duoc.fichas_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Fichas Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Fichas del casino.

                        Permite:
                        - Registrar Fichas
                        - Consultar Fichas
                        - Eliminar Fichas
                        - Actualzar Fichas
                        - Listar la informacion de sus costos y sus colores
                        """,
				contact = @Contact(
						name = "Gabriel fernandez",
						email = "ga.fernandezv@duocuc.cl"
				)
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class FichasCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FichasCasinoApplication.class, args);
	}

}
