package cl.duoc.cuentas_casino.repository;

import cl.duoc.cuentas_casino.model.Cuenta_Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Cuenta_ClienteRepository extends JpaRepository<Cuenta_Cliente, Long> {


}
