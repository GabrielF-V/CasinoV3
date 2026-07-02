package cl.duoc.deudores_casino.service;

import cl.duoc.deudores_casino.clients.ClienteFeign;
import cl.duoc.deudores_casino.dto.ClienteDTO;
import cl.duoc.deudores_casino.dto.DeudorDTO;
import cl.duoc.deudores_casino.mapper.DeudorMapper;
import cl.duoc.deudores_casino.model.Deudor;
import cl.duoc.deudores_casino.repository.DeudorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DeudorService {
    @Autowired
    DeudorRepository deudorRepository;
    
    @Autowired
    DeudorMapper deudorMapper;

    @Autowired
    private ClienteFeign clienteFeign;

    public List<Deudor> findAll(){
        return deudorRepository.findAll();
    }

    public Deudor findById(Long id_deudor){
        return deudorRepository.findById(id_deudor).orElse(null);
    }

    public Deudor save(Deudor deudor){return deudorRepository.save(deudor);}

    public Deudor update(Long id_deudor, Deudor deudor){
        Deudor deudorActualizar = deudorRepository.findById(id_deudor).orElse(null);
        if (deudorActualizar == null) return null;
        deudorActualizar.setCant_deuda(deudor.getCant_deuda());
        deudorActualizar.setBan(deudor.isBan());
        deudorActualizar.setCliente(deudor.getCliente());
        return deudorRepository.save(deudor);
    }
    public void delete(Long id_deudor){
        deudorRepository.deleteById(id_deudor);
    }
    public DeudorDTO findDTO(Long id){
        return deudorMapper.toDTO(findById(id));
    }

    public List<DeudorDTO> findDTOList(){
        return deudorMapper.toDTOList(findAll());
    }

}
