package cl.duoc.cuentas_casino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cuentas_clientes")
public class Cuenta_Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 50, message = "el nombre de usuario es de 4 a 20 caracteres")
    private String nom_usuario;

    @Column(name = "contrasena")
    @NotBlank
    @Size(min = 8, max = 50, message = "la contraseña debe ser de 8 a 14 caracteres")
    private String contraseña;

    private Long cliente;

}
