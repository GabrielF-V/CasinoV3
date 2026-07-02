package cl.duoc.empleado_casino.repository;

import cl.duoc.empleado_casino.model.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, String> {
}
