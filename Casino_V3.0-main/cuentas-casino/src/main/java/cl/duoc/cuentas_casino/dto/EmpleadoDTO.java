package cl.duoc.cuentas_casino.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmpleadoDTO {
    private String nombre;
    private String apellido;
    private String gmail;
}
