package cl.duoc.reportes_service.clients;


import cl.duoc.reportes_service.dto.FichaDTO;
import cl.duoc.reportes_service.dto.JuegoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "juegos-casino", path = "api/v1/juegos")
public interface JuegoFeign {

    @GetMapping("/listado")
    List<JuegoDTO> listadoJuegos();
}
