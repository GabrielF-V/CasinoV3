package cl.duoc.reportes_service.dto;

import cl.duoc.reportes_service.clients.EmpleadoFeign;
import lombok.Data;

@Data
public class Cuenta_EmpleadoDTO {
    private String nom_usuario;
    private String empleado_email;
}
