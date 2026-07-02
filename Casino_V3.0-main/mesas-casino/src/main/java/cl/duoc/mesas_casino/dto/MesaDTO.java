package cl.duoc.mesas_casino.dto;

import lombok.Data;

@Data
public class MesaDTO {
    private Long id_mesa;
    private String nombre_cliente;
    private String nombre_juego;
    private String nombre_empleado;
}
