package cl.duoc.cuentas_casino.mapper;

import cl.duoc.cuentas_casino.client.EmpleadoFeign;
import cl.duoc.cuentas_casino.dto.Cuenta_EmpleadoDTO;
import cl.duoc.cuentas_casino.dto.EmpleadoDTO;
import cl.duoc.cuentas_casino.model.Cuenta_Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cuenta_empleadomapper {

    @Autowired
    private EmpleadoFeign empleadoFeign;

    public Cuenta_EmpleadoDTO toDTO(Cuenta_Empleado cuenta_empleado){
        if (cuenta_empleado==null) return null;
        Cuenta_EmpleadoDTO dto = new Cuenta_EmpleadoDTO();
        dto.setNom_usuario(cuenta_empleado.getNom_usuario());

        EmpleadoDTO empleadoDTO = null;
        if (cuenta_empleado.getEmpleado() != null) {
            empleadoDTO= empleadoFeign.buscarPorID(cuenta_empleado.getEmpleado());
            if (empleadoDTO != null) {
                dto.setEmpleado_email(empleadoDTO.getGmail());
            } else {
                dto.setEmpleado_email("Empleado no encontrado");
            }
        } else {
            dto.setEmpleado_email("Sin asignar");
        }

        return dto;
    }

    public List<Cuenta_EmpleadoDTO> toDTOlist(List<Cuenta_Empleado> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }

}
