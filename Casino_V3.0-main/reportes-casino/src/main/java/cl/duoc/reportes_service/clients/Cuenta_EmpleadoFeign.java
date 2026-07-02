package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.Cuenta_EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "cuentas-casino", path = "api/v1/cuentas_empleados")
public interface Cuenta_EmpleadoFeign {

    @GetMapping("/listado")
    List<Cuenta_EmpleadoDTO> listadoCuenta_Empleado();
}
