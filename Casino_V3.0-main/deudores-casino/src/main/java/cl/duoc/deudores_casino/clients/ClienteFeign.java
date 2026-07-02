package cl.duoc.deudores_casino.clients;


import cl.duoc.deudores_casino.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes-casino")
public interface ClienteFeign {
    @GetMapping("/api/v1/clientes/{id}")
    ClienteDTO buscarClientePorId(@PathVariable("id") Long id);
}
