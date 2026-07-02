package cl.duoc.deudores_casino.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data

public class DeudorDTO {
    private Long id_deudor;
    private String nombre_cliente;
    private int cant_deuda;
    private boolean ban;
}
