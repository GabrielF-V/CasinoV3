package cl.duoc.cuentas_casino.dto;


import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private int cantidad_fichas;

}
