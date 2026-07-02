package cl.duoc.deudores_casino.mapper;

import cl.duoc.deudores_casino.clients.ClienteFeign;
import cl.duoc.deudores_casino.dto.ClienteDTO;
import cl.duoc.deudores_casino.dto.DeudorDTO;
import cl.duoc.deudores_casino.model.Deudor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeudorMapper {

    @Autowired
    private ClienteFeign clienteFeign;

    public DeudorDTO toDTO(Deudor deudor){
        if (deudor == null) {return null;}

        DeudorDTO dto = new DeudorDTO();
        dto.setId_deudor(deudor.getId_deudor());
        dto.setCant_deuda(deudor.getCant_deuda());
        dto.setBan(deudor.isBan());

        ClienteDTO clienteDTO = null;
        if (deudor.getCliente() != null) {
            clienteDTO = clienteFeign.buscarClientePorId(deudor.getCliente());
            if (clienteDTO != null) {
                dto.setNombre_cliente(clienteDTO.getNombre_completo());
            } else {
                dto.setNombre_cliente("Cliente no encontrado");
            }
        } else {
            dto.setNombre_cliente("Sin asignar");
        }
        return dto;
    }
    public List<DeudorDTO> toDTOList(List<Deudor> listado){
        return listado.stream().map(this::toDTO).toList();
    }

}
