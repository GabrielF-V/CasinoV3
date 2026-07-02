package cl.duoc.fichas_casino.mapper;

import cl.duoc.fichas_casino.dto.FichaDTO;
import cl.duoc.fichas_casino.model.Ficha;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FichaMapper {

    public FichaDTO toDTO(Ficha ficha) {
        if(ficha == null) {
            return null;
        }
        FichaDTO dto = new FichaDTO();
        dto.setColor(ficha.getColor().getNombre());
        dto.setValor(ficha.getColor().getValor());
        dto.setDescripcion(ficha.getColor().getDescripcion());
        return dto;
    }

    public List<FichaDTO> toDTOlist(List<Ficha> listado) {
        return listado.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
}
