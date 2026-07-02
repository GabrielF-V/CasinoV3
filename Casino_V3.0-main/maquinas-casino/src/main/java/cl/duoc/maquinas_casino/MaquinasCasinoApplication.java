package cl.duoc.maquinas_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Maquinas Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Maquinas del casino.

                        Permite:
                        - Registrar Maquinas
                        - Consultar Maquinas
                        - Eliminar Maquinas
                        - Actualizar Maquinas
                        - Listar Maquinas Usadas por clientes
                        """,
				contact = @Contact(
						name = "Gabriel Fernandez",
						email = "ga.fernandez@duocuc.cl"
				)
		)
)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MaquinasCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaquinasCasinoApplication.class, args);
	}

}
