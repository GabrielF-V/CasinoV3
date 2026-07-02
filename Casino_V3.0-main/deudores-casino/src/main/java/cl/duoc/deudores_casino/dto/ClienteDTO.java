package cl.duoc.deudores_casino.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private int cantidad_fichas;

    public String getNombre_completo() {
        String n = (this.nombre != null) ? this.nombre : "";
        String a = (this.apellido != null) ? this.apellido : "";
        return (n + " " + a).trim();
    }
}

