package cl.duoc.clientes_casino.service;

import cl.duoc.clientes_casino.dto.ClienteDTO;
import cl.duoc.clientes_casino.exception.ClienteMenorEdadException;
import cl.duoc.clientes_casino.mapper.Clientemapper;
import cl.duoc.clientes_casino.model.Cliente;
import cl.duoc.clientes_casino.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private Clientemapper clientemapper;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElse(null);

    }

    public Cliente save(Cliente cliente){

        int edad = cliente.getEdad();
        if (edad<18) throw new ClienteMenorEdadException("El cliente es menor de edad.");

        
        return clienteRepository.save(cliente);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public Cliente update(Long id,Cliente cliente){
        if(!clienteRepository.existsById(id)){
            return null;
        }
        Cliente clienteActualizado = clienteRepository.findById(id).orElse(null);
        clienteActualizado.setEmail(cliente.getEmail());
        clienteActualizado.setEdad(cliente.getEdad());
        clienteActualizado.setCantidad_fichas(cliente.getCantidad_fichas());

        return clienteRepository.save(clienteActualizado);
    }


    public List<ClienteDTO> findDTOList(){
        return clientemapper.toDTOlist(findAll());
    }

    public Cliente findByEmail(String email){
        return clienteRepository.findByEmail(email);
    }
}
