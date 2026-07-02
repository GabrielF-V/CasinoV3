package cl.duoc.mesas_casino.clients;

import cl.duoc.mesas_casino.dto.JuegoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "juegos-casino")
public interface JuegoFeign {
    @GetMapping("/api/v1/juegos/{id}")
    JuegoDTO buscarJuegoPorId(@PathVariable("id") Long id);
}
