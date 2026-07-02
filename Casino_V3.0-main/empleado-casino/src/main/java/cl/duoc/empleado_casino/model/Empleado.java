package cl.duoc.empleado_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    @Email(message = "El formato del texto tiene que ser en gmail")
    private String gmail;

    @ManyToOne
    @JoinColumn(name = "puesto_codigo")
    private Puesto puesto;

}
