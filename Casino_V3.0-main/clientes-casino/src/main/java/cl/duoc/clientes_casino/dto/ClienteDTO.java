package cl.duoc.clientes_casino.dto;

import lombok.Data;

@Data

public class ClienteDTO {
    private String nombre_completo;
    private String email;
    private int edad;
    private int cantidad_fichas;
}
