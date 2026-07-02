package cl.duoc.cuentas_casino.service;

import cl.duoc.cuentas_casino.client.ClienteFeign;
import cl.duoc.cuentas_casino.dto.ClienteDTO;
import cl.duoc.cuentas_casino.dto.Cuenta_ClienteDTO;
import cl.duoc.cuentas_casino.dto.Cuenta_EmpleadoDTO;
import cl.duoc.cuentas_casino.mapper.Cuenta_clientemapper;
import cl.duoc.cuentas_casino.model.Cuenta_Cliente;
import cl.duoc.cuentas_casino.repository.Cuenta_ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cuenta_ClienteService {

    @Autowired
    private Cuenta_ClienteRepository cuenta_clienteRepository;

    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private Cuenta_clientemapper cuenta_clientemapper;

    public List<Cuenta_Cliente> findAll(){
        return cuenta_clienteRepository.findAll();
    }

    public Cuenta_Cliente findById(Long id){
        return cuenta_clienteRepository.findById(id).orElse(null);
    }

    public Cuenta_Cliente save(Cuenta_Cliente cc){
        return cuenta_clienteRepository.save(cc);
    }

    public void delete(Long id){
        cuenta_clienteRepository.deleteById(id);
    }

    public Cuenta_Cliente update(Long id, Cuenta_Cliente cc){
        if(!cuenta_clienteRepository.existsById(id)){
            return null;
        }
        Cuenta_Cliente cuenta_clienteupdate = cuenta_clienteRepository.findById(id).orElse(null);
        cuenta_clienteupdate.setNom_usuario(cc.getNom_usuario());
        cuenta_clienteupdate.setContraseña(cc.getContraseña());
        cuenta_clienteupdate.setCliente(cc.getCliente());

        return cuenta_clienteRepository.save(cuenta_clienteupdate);
    }

    public List<Cuenta_ClienteDTO> findDTOList(){
        return cuenta_clientemapper.toDTOlist(findAll());
    }

}
