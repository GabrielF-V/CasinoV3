package cl.duoc.reportes_service.dto;

import lombok.Data;

@Data
public class MaquinaDTO {
    private int costo;
    private int pozo;
    private String nombre_tipo;
    private String nombre_cliente;
}
