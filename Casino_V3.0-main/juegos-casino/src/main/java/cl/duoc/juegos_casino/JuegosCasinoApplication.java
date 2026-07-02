package cl.duoc.juegos_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Juegos Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Juegos del casino.

                        Permite:
                        - Registrar Juegos
                        - Consultar Juegos
                        - Eliminar Juegos
                        - Actualizar Juegos
                        - Listar Juegos
                        """,
				contact = @Contact(
						name = "Gabriel Fernandez",
						email = "ga.fernandez@duocuc.cl"
				)
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class JuegosCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuegosCasinoApplication.class, args);
	}

}
