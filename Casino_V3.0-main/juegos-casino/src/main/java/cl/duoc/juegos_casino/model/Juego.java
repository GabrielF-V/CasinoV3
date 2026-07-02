package cl.duoc.juegos_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "el nombre no debe estar en blanco")
    @Size(min = 5,max = 25, message = "no debe superar los 25 caracteres")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

}
