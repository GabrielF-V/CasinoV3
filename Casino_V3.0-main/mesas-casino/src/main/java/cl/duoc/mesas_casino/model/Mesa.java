package cl.duoc.mesas_casino.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "mesas")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mesa;
    @Column(name = "id_juego")
    private Long juego;
    @Column(name = "id_cliente")
    private Long cliente;
    @Column(name = "id_empleado")
    private Long empleado;
}
