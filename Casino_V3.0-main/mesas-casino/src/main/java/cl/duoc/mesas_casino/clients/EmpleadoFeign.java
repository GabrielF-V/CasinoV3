package cl.duoc.mesas_casino.clients;

import cl.duoc.mesas_casino.dto.EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "empleado-casino")
public interface EmpleadoFeign {
    @GetMapping("/api/v1/empleados/{id}")
    EmpleadoDTO buscarEmpleadoPorId(@PathVariable("id") Long id);
}
