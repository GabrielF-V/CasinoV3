package cl.duoc.mesas_casino.dto;

import lombok.Data;

@Data
public class EmpleadoDTO {

    private String nombre;
    private String apellido;
    private String gmail;



    public String getNombre_completo() {
        String n = (this.nombre != null) ? this.nombre : "";
        String a = (this.apellido != null) ? this.apellido : "";
        return (n + " " + a).trim();
    }

}
