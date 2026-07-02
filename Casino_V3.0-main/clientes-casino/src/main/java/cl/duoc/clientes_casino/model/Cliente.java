package cl.duoc.clientes_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100, message = "El nombre no puede superar los 20 caracteres")
    private String nombre;

    @Size(max = 100, message = "no puede tener mas de 20 caracteres")
    private String apellido;

    @NotNull
    private int edad;

    @Email(message = "El formato no esta hecho en Email")
    private String email;

    @Column(name = "cantidad_fichas")
    @Positive(message = "La cantidad e fichas tiene que ser positiva")
    private int cantidad_fichas;
}
