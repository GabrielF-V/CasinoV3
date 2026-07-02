package cl.duoc.juegos_casino.repository;

import cl.duoc.juegos_casino.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepository extends JpaRepository<Juego,Long> {
}
