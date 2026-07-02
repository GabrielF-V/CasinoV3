package cl.duoc.empleado_casino.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "puestos")
public class Puesto {

    @Id
    @Column(name = "puesto_codigo")
    private String puesto_codigo;

    @Size(max = 15, message = "no puede superar los 7 caracteres")
    private String nombre;
}
