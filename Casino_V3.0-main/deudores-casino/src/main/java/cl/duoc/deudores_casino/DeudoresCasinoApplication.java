package cl.duoc.deudores_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Deudores Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Deudores del casino.

                        Permite:
                        - Registrar Deudores
                        - Consultar Deudores
                        - Eliminar Deudores
                        - Actualzar Deudores
                        - Listar la informacion sobre los deudores y que clientes son
                        """,
				contact = @Contact(
						name = "Gabriel fernandez",
						email = "ga.fernandezv@duocuc.cl"
				)
		)
)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DeudoresCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeudoresCasinoApplication.class, args);
	}

}
