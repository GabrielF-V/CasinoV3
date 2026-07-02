package cl.duoc.juegos_casino.mapper;

import cl.duoc.juegos_casino.dto.JuegoDTO;
import cl.duoc.juegos_casino.model.Juego;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JuegoMapper {
    public JuegoDTO toDTO(Juego juego) {
        if(juego == null) {
            return null;
        }
        JuegoDTO dto = new JuegoDTO();
        dto.setId(juego.getId());
        dto.setNombre(juego.getNombre());
        dto.setTipo(String.valueOf(juego.getTipo()));
        return dto;
    }

    public List<JuegoDTO> toDTOlist(List<Juego> listado) {
        return listado.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
