package cl.duoc.empleado_casino.service;

import cl.duoc.empleado_casino.dto.EmpleadoDTO;
import cl.duoc.empleado_casino.mapper.EmpleadoMapper;
import cl.duoc.empleado_casino.model.Empleado;
import cl.duoc.empleado_casino.model.Puesto;
import cl.duoc.empleado_casino.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpleadoMapper empleadoMapper;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Empleado findById(Long id){
        return empleadoRepository.findById(id).orElse(null);

    }

    public Empleado save(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public void delete(Long id){
        empleadoRepository.deleteById(id);
    }

    public  Empleado update(Long id, Empleado empleado){
        if(!empleadoRepository.existsById(id)){
            return null;
        }
        Empleado empleadoactualizar = empleadoRepository.findById(id).orElse(null);
        empleadoactualizar.setGmail(empleado.getGmail());
        empleadoactualizar.setPuesto(empleado.getPuesto());

        return empleadoRepository.save(empleadoactualizar);
    }


    public List<EmpleadoDTO> findDTOList(){
        return empleadoMapper.toDTOlist(findAll());
    }

}
