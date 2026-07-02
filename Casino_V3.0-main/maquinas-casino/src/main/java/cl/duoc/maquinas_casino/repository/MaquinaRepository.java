package cl.duoc.maquinas_casino.repository;

import cl.duoc.maquinas_casino.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MaquinaRepository extends JpaRepository<Maquina,Long> {

}
