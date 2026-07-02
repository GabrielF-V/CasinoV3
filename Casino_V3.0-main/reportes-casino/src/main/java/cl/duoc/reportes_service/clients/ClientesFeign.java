package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "clientes-casino", path = "api/v1/clientes")
public interface ClientesFeign {

    @GetMapping("/listado")
    List<ClienteDTO> listadoEstudiante();
}
