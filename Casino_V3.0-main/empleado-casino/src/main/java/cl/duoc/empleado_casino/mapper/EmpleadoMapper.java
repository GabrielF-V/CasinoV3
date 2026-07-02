package cl.duoc.empleado_casino.mapper;

import cl.duoc.empleado_casino.dto.EmpleadoDTO;
import cl.duoc.empleado_casino.model.Empleado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpleadoMapper {
    public EmpleadoDTO toDTO(Empleado empleado){
        if (empleado==null) return null;
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setNombre_completo(empleado.getNombre() + ' ' + empleado.getApellido());
        dto.setGmail(empleado.getGmail());
        dto.setPuesto(empleado.getPuesto().getPuesto_codigo());
        return dto;
    }

    public List<EmpleadoDTO> toDTOlist(List<Empleado> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
