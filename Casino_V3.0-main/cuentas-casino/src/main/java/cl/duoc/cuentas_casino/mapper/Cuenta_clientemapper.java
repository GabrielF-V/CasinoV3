package cl.duoc.cuentas_casino.mapper;

import cl.duoc.cuentas_casino.client.ClienteFeign;
import cl.duoc.cuentas_casino.dto.ClienteDTO;
import cl.duoc.cuentas_casino.dto.Cuenta_ClienteDTO;
import cl.duoc.cuentas_casino.model.Cuenta_Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cuenta_clientemapper {

    @Autowired
    private ClienteFeign clienteFeign;

    public Cuenta_ClienteDTO toDTO(Cuenta_Cliente cuenta_cliente){
        if (cuenta_cliente==null) return null;
        Cuenta_ClienteDTO dto = new Cuenta_ClienteDTO();
        dto.setNom_usuario(cuenta_cliente.getNom_usuario());

        ClienteDTO clienteDTO = null;
        if (cuenta_cliente.getCliente() != null) {
            clienteDTO = clienteFeign.buscarPorID(cuenta_cliente.getCliente());
            if (clienteDTO != null) {
                dto.setCliente_email(clienteDTO.getEmail());
            } else {
                dto.setCliente_email("Cliente no encontrado");
            }
        } else {
            dto.setCliente_email("Sin asignar");
        }
        return dto;
    }

    public List<Cuenta_ClienteDTO> toDTOlist(List<Cuenta_Cliente> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
