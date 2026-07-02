package cl.duoc.fichas_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "colores")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nro_color;


    private String nombre;

    @Positive(message = "El precio debe ser mayor a 0 ")
    private int valor;

    @NotBlank(message = "La descripcion nopuede estar en blanco")
    @Size(max = 80, message = "El nombre no puede tener mas de 80 caracteres")
    private String descripcion;
}
