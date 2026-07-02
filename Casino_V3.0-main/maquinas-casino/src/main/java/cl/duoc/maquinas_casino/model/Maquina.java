package cl.duoc.maquinas_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "maquinas")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_maquina;
    @Positive
    private int costo;
    @Positive
    private int pozo;
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    @Column(name = "id_cliente")
    private Long cliente;

}
