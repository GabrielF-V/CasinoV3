package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.MaquinaDTO;
import cl.duoc.reportes_service.dto.MesaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "mesas-casino", path = "api/v1/mesas")
public interface MesaFeign {

    @GetMapping("/listado")
    List<MesaDTO> listadoMesas();
}
