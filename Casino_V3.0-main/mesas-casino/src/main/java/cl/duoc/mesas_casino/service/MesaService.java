package cl.duoc.mesas_casino.service;

import cl.duoc.mesas_casino.clients.ClienteFeign;
import cl.duoc.mesas_casino.clients.EmpleadoFeign;
import cl.duoc.mesas_casino.clients.JuegoFeign;
import cl.duoc.mesas_casino.dto.ClienteDTO;
import cl.duoc.mesas_casino.dto.EmpleadoDTO;
import cl.duoc.mesas_casino.dto.JuegoDTO;
import cl.duoc.mesas_casino.dto.MesaDTO;
import cl.duoc.mesas_casino.mapper.MesaMapper;
import cl.duoc.mesas_casino.model.Mesa;
import cl.duoc.mesas_casino.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private JuegoFeign juegoFeign;
    @Autowired
    private EmpleadoFeign empleadoFeign;
    @Autowired
    private MesaMapper mesaMapper;

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void delete(Long id){
        mesaRepository.deleteById(id);
    }

    public Mesa findById(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }
    
    public Mesa update(Long id, Mesa mesa){
        if (!mesaRepository.existsById(id)) return null;
        Mesa mesaActualizar = mesaRepository.findById(id).orElse(null);
        mesaActualizar.setEmpleado(mesa.getEmpleado());
        mesaActualizar.setJuego(mesa.getJuego());
        mesaActualizar.setCliente(mesa.getCliente());

        return mesaRepository.save(mesaActualizar);
    }

    public MesaDTO findDTO(Long id){
        return mesaMapper.toDTO(findById(id));
    }

    public List<MesaDTO> findDTOList(){
        return mesaMapper.toDTOlist(findAll());
    }


}
