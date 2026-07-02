package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.ClienteDTO;
import cl.duoc.reportes_service.dto.DeudorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "deudores-casino", path = "api/v1/deudores")
public interface DeudorFeign {

    @GetMapping("/listado")
    List<DeudorDTO> listadoDeudor();
}
