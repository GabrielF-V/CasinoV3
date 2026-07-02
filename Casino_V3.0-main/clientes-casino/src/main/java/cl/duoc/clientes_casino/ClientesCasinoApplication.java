package cl.duoc.clientes_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Cliente Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Clientes del casino.

                        Permite:
                        - Registrar Clientes
                        - Consultar Clientes
                        - Eliminar Clientes
                        - Actualizar Clientes
                        - Listar Clientes con datos completos y solo datos unicos
                        """,
				contact = @Contact(
						name = "Adam Verdugo",
						email = "ad.verdugo@duocuc.cl"
				)
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class ClientesCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesCasinoApplication.class, args);
	}

}
