package cl.duoc.deudores_casino.repository;

import cl.duoc.deudores_casino.model.Deudor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeudorRepository extends JpaRepository<Deudor,Long> {
}
