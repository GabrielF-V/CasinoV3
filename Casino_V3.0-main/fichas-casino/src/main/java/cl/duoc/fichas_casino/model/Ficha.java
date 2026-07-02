package cl.duoc.fichas_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "fichas")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nro_ficha;


    @Positive
    private int cantidad;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "nro_color")
    private Color color;
}
