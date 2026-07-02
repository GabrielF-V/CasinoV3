package cl.duoc.mesas_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Mesas Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Mesas del casino.

                        Permite:
                        - Registrar Mesas
                        - Consultar Mesas
                        - Eliminar Mesas
                        - Actualizar Mesas
                        - Listar informacion sobre los clientes que las estan usando los juegos que contiene y el empleado asignado
                        """,
				contact = @Contact(
						name = "Adam Verdugo",
						email = "ad.verdugo@duocuc.cl"
				)
		)
)
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MesasCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MesasCasinoApplication.class, args);
	}

}
