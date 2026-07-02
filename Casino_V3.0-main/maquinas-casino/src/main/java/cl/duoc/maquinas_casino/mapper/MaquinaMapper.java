package cl.duoc.maquinas_casino.mapper;

import cl.duoc.maquinas_casino.clients.ClienteFeign;
import cl.duoc.maquinas_casino.dto.ClienteDTO;
import cl.duoc.maquinas_casino.dto.MaquinaDTO;
import cl.duoc.maquinas_casino.model.Maquina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class MaquinaMapper {

    @Autowired
    private ClienteFeign clienteFeign;

    public MaquinaMapper(ClienteFeign clienteFeign) {
        this.clienteFeign = clienteFeign;
    }

    public MaquinaDTO toDTO (Maquina maquina){
        if (maquina == null) { return null;}
        MaquinaDTO dto = new MaquinaDTO();
        dto.setCosto(maquina.getCosto());
        dto.setPozo(maquina.getPozo());
        dto.setNombre_tipo(maquina.getTipo().getNombre_tipo());

        ClienteDTO clienteDTO = null;
        if(maquina.getCliente() != null) {
            clienteDTO = clienteFeign.buscarClientePorId(maquina.getCliente());
        }
        if (clienteDTO != null) {
            // Asumiendo que tu DTO tiene un campo para el nombre del cliente
            dto.setNombre_cliente(clienteDTO.getNombre_completo());
            // setea aquí cualquier otro dato del cliente que necesites...
        } else {
            // Opcional: Qué hacer si no hay cliente (puedes dejarlo nulo o poner un texto por defecto)
            dto.setNombre_cliente("Sin asignar");
        }
        return dto;

    }
    public List<MaquinaDTO> toDTOList (List<Maquina> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
