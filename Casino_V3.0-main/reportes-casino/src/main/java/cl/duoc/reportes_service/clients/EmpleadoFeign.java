package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "empleado-casino", path = "api/v1/empleados")
public interface EmpleadoFeign {

    @GetMapping("/listado")
    List<EmpleadoDTO> listadoEmpleado();

}
