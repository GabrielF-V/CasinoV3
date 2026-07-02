package cl.duoc.cuentas_casino.repository;

import cl.duoc.cuentas_casino.model.Cuenta_Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cuenta_EmpleadoRepository extends JpaRepository<Cuenta_Empleado, Long> {


}
