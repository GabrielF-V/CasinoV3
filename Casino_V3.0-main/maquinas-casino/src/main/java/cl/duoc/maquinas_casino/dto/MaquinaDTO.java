package cl.duoc.maquinas_casino.dto;


import cl.duoc.maquinas_casino.model.Tipo;
import lombok.Data;

import java.util.List;

@Data

public class MaquinaDTO {
    private int costo;
    private int pozo;
    private String nombre_tipo;
    private String nombre_cliente;
}
