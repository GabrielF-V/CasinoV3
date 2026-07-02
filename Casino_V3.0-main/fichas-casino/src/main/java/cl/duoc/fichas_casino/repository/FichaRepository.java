package cl.duoc.fichas_casino.repository;

import cl.duoc.fichas_casino.model.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaRepository extends JpaRepository<Ficha, Long> {
}
