package cl.duoc.fichas_casino.service;

import cl.duoc.fichas_casino.dto.FichaDTO;
import cl.duoc.fichas_casino.mapper.FichaMapper;
import cl.duoc.fichas_casino.model.Ficha;
import cl.duoc.fichas_casino.repository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private FichaMapper fichaMapper;

    public List<Ficha> findAll(){
        return fichaRepository.findAll();
    }

    public Ficha findByNro(Long nro_ficha){
        return fichaRepository.findById(nro_ficha).orElse(null);
    }

    public Ficha save(Ficha ficha){
        return fichaRepository.save(ficha);
    }

    public void delete(Long nro_ficha){
        fichaRepository.deleteById(nro_ficha);
    }

    public FichaDTO findDTO(Long nro_ficha){
        return fichaMapper.toDTO(findByNro(nro_ficha));
    }

    public List<FichaDTO> findDTOList(){
        return fichaMapper.toDTOlist(findAll());
    }

}
