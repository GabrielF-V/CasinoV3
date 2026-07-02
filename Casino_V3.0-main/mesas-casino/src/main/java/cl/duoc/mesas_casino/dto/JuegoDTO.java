package cl.duoc.mesas_casino.dto;

import lombok.Data;

@Data

public class JuegoDTO {
    private Long id;
    private String nombre;
    private TipoJuegoDTO tipo;

}
