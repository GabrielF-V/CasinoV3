package cl.duoc.maquinas_casino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tipos")
public class Tipo {
    @Id
    private Long id_tipo;
    @NotBlank(message = "el nombre no debe estar en blanco")
    private String nombre_tipo;
    private String mecanismo;
}
