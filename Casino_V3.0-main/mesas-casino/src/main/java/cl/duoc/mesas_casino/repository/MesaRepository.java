package cl.duoc.mesas_casino.repository;

import cl.duoc.mesas_casino.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa,Long> {
}
