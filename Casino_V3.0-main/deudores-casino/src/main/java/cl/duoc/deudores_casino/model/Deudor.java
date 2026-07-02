package cl.duoc.deudores_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deudores")
public class Deudor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_deudor;
    @Positive
    private int cant_deuda;
    private boolean ban;
    private Long cliente;
}
