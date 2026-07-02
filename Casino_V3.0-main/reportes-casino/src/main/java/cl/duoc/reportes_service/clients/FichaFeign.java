package cl.duoc.reportes_service.clients;

import cl.duoc.reportes_service.dto.FichaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "fichas-casino", path = "api/v1/fichas")
public interface FichaFeign {

    @GetMapping("/listado")
    List<FichaDTO> listadoFichas();


}
