package cl.duoc.juegos_casino.service;

import cl.duoc.juegos_casino.dto.JuegoDTO;
import cl.duoc.juegos_casino.mapper.JuegoMapper;
import cl.duoc.juegos_casino.model.Juego;
import cl.duoc.juegos_casino.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private JuegoMapper juegoMapper;

    public List<Juego> findAll(){
        return juegoRepository.findAll();
    }
    public Juego findById(Long id){
        return juegoRepository.findById(id).orElse(null);
    }
    
    public Juego save(Juego juego){
        return juegoRepository.save(juego);
    }
    
    public Juego update (Long id, Juego juego){
        Juego juegoActualizar =juegoRepository.findById(id).orElse(null);
        if (juegoActualizar == null) return null;
        juegoActualizar.setNombre(juego.getNombre());
        juegoActualizar.setTipo(juego.getTipo());
        return juegoRepository.save(juego);
        }
    public void delete(Long id){
        juegoRepository.deleteById(id);
    }

    public JuegoDTO findDTO(Long id){
        return juegoMapper.toDTO(findById(id));
    }

    public List<JuegoDTO> findDTOList(){
        return juegoMapper.toDTOlist(findAll());
    }

}
