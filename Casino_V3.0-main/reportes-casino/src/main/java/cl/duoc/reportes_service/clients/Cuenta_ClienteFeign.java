package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.ClienteDTO;
import cl.duoc.reportes_service.dto.Cuenta_ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cuentas-casino-cuenta-cliente", path = "api/v1/cuentas_clientes")
public interface Cuenta_ClienteFeign {

    @GetMapping("/listado")
    List<Cuenta_ClienteDTO> listadoCuenta_Cliente();
}
