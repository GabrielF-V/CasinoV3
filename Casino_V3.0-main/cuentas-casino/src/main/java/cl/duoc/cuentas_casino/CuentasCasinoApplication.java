package cl.duoc.cuentas_casino;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Cuentas Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Cuentas del casino.

                        Permite:
                        - Registrar cuentas de clientes y empleados
                        - Consultar cuentas de clientes y empleados
                        - Eliminar cuentas de clientes y empleados
                        - Actualizar cuentas de clientes y empleados
                        - Listar cuentas de clientes y empleados
                        """,
				contact = @Contact(
						name = "Adam Verdugo",
						email = "ad.verdugo@duocuc.cl"
				)
		)
)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CuentasCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentasCasinoApplication.class, args);
	}

}
