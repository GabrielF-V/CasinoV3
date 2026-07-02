package cl.duoc.reportes_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Reportes Service API",
				version = "2.0.0",
				description = """
                        API REST para la gestión de Reportes del casino.

                        Permite:
                        - Lista la informacion de todos los servicios siguientes clientes 
                          empelados deudores fichas juegos maquinas mesas y cuentas
                        """,
				contact = @Contact(
						name = "Adam Verdugo" + "Gabriel Fernandez",
						email = "ad.verdugo@duocuc.cl" + "ga.fernandezv@duocuc.cl"
				)
		)
)
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ReportesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportesServiceApplication.class, args);
	}

}
