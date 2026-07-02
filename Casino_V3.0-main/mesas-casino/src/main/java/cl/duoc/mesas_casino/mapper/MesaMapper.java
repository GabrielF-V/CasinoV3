package cl.duoc.mesas_casino.mapper;

import cl.duoc.mesas_casino.clients.ClienteFeign;
import cl.duoc.mesas_casino.clients.EmpleadoFeign;
import cl.duoc.mesas_casino.clients.JuegoFeign;
import cl.duoc.mesas_casino.dto.ClienteDTO;
import cl.duoc.mesas_casino.dto.EmpleadoDTO;
import cl.duoc.mesas_casino.dto.JuegoDTO;
import cl.duoc.mesas_casino.dto.MesaDTO;
import cl.duoc.mesas_casino.model.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MesaMapper {

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private EmpleadoFeign empleadoFeign;

    @Autowired
    private JuegoFeign juegoFeign;

    public MesaDTO toDTO(Mesa mesa){
        if(mesa == null) {
            return null;
        }

        MesaDTO dto = new MesaDTO();
        dto.setId_mesa(mesa.getId_mesa());

        ClienteDTO clienteDTO = null;
        if (mesa.getCliente() != null) {
            clienteDTO = clienteFeign.buscarClientePorId(mesa.getCliente());
            if (clienteDTO != null) {
                dto.setNombre_cliente(clienteDTO.getNombre_completo());
            } else {
                dto.setNombre_cliente("Cliente no encontrado");
            }
        } else {
            dto.setNombre_cliente("Sin asignar");
        }

        JuegoDTO juegoDTO = null;
        if (mesa.getJuego() != null) {
            juegoDTO = juegoFeign.buscarJuegoPorId(mesa.getJuego());
            if (juegoDTO != null) {
                dto.setNombre_juego(juegoDTO.getNombre());
            } else {
                dto.setNombre_juego("Juego no encontrado");
            }
        } else {
            dto.setNombre_juego("Sin asignar");
        }

        EmpleadoDTO empleadoDTO = null;
        if (mesa.getEmpleado() != null) {
            empleadoDTO= empleadoFeign.buscarEmpleadoPorId(mesa.getEmpleado());
            if (empleadoDTO != null) {
                dto.setNombre_empleado(empleadoDTO.getNombre_completo());
            } else {
                dto.setNombre_empleado("Empleado no encontrado");
            }
        } else {
            dto.setNombre_empleado("Sin asignar");
        }

        return dto;
    }
    public List<MesaDTO> toDTOlist(List<Mesa> listado) {
        return listado.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
