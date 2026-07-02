package cl.duoc.empleado_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Empleados Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Empleados del casino.

                        Permite:
                        - Registrar Empleados
                        - Consultar Empleados
                        - Eliminar Empleados
                        - Actualzar Empleados
                        - Listar la informacion de los empleados
                        """,
				contact = @Contact(
						name = "Adam Verdugo",
						email = "ad.verdugo@duocuc.cl"
				)
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class EmpleadoCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadoCasinoApplication.class, args);
	}

}
