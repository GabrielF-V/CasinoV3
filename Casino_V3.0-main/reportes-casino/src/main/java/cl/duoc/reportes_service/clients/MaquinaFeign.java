package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.MaquinaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "maquinas-casino", path = "api/v1/maquinas")
public interface MaquinaFeign {

    @GetMapping("/listado")
    List<MaquinaDTO> listadoMaquina();
}
