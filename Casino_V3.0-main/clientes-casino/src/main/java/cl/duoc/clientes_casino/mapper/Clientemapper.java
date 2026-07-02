package cl.duoc.clientes_casino.mapper;

import cl.duoc.clientes_casino.dto.ClienteDTO;
import cl.duoc.clientes_casino.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class Clientemapper {
    public ClienteDTO toDTO(Cliente cliente){
        if (cliente==null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre_completo(cliente.getNombre() + ' ' + cliente.getApellido());
        dto.setEdad(cliente.getEdad());
        dto.setCantidad_fichas(cliente.getCantidad_fichas());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

    public List<ClienteDTO> toDTOlist(List<Cliente> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }

}
