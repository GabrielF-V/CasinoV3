package cl.duoc.maquinas_casino.service;

import cl.duoc.maquinas_casino.clients.ClienteFeign;
import cl.duoc.maquinas_casino.dto.ClienteDTO;
import cl.duoc.maquinas_casino.dto.MaquinaDTO;
import cl.duoc.maquinas_casino.mapper.MaquinaMapper;
import cl.duoc.maquinas_casino.model.Maquina;
import cl.duoc.maquinas_casino.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MaquinaService {
    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private MaquinaMapper maquinaMapper;

    @Autowired
    private ClienteFeign clienteFeign;

    public List<Maquina> findAll(){
        return maquinaRepository.findAll();
    }

    public Maquina findById(Long id_maquina){
        return maquinaRepository.findById(id_maquina).orElse(null);
    }

    public Maquina save(Maquina maquina){
        return maquinaRepository.save(maquina);
    }

    public Maquina update(Long id_maquina, Maquina maquina){
        Maquina maquinaActualizar = maquinaRepository.findById(id_maquina).orElse(null);
        if (maquinaActualizar == null) return null;
        maquinaActualizar.setCosto(maquina.getCosto());
        maquinaActualizar.setPozo(maquina.getPozo());
        maquinaActualizar.setTipo(maquina.getTipo());
        maquinaActualizar.setCliente(maquina.getCliente());
        return maquinaRepository.save(maquina);
    }
    public void delete(Long id_maquina){
        maquinaRepository.deleteById(id_maquina);
    }

    public MaquinaDTO findDTO(Long id){
        return maquinaMapper.toDTO(findById(id));
    }

    public List<MaquinaDTO> findDTOList(){
        return maquinaMapper.toDTOList(findAll());
    }


}
