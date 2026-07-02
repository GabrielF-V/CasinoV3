package cl.duoc.cuentas_casino.service;

import cl.duoc.cuentas_casino.client.EmpleadoFeign;
import cl.duoc.cuentas_casino.dto.EmpleadoDTO;
import cl.duoc.cuentas_casino.dto.Cuenta_EmpleadoDTO;
import cl.duoc.cuentas_casino.mapper.Cuenta_empleadomapper;
import cl.duoc.cuentas_casino.model.Cuenta_Empleado;
import cl.duoc.cuentas_casino.repository.Cuenta_EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cuenta_EmpleadoService {

    @Autowired
    private Cuenta_EmpleadoRepository cuenta_empleadoRepository;

    @Autowired
    private EmpleadoFeign empleadoFeign;
    @Autowired
    private Cuenta_empleadomapper cuenta_empleadomapper;

    public List<Cuenta_Empleado> findAll(){
        return cuenta_empleadoRepository.findAll();
    }

    public Cuenta_Empleado findById(Long id){
        return cuenta_empleadoRepository.findById(id).orElse(null);
    }

    public Cuenta_Empleado save(Cuenta_Empleado ce){
        return cuenta_empleadoRepository.save(ce);
    }

    public void delete(Long id){
        cuenta_empleadoRepository.deleteById(id);
    }

    public Cuenta_Empleado update(Long id, Cuenta_Empleado ce){
        if(!cuenta_empleadoRepository.existsById(id)){
            return null;
        }
        Cuenta_Empleado cuenta_empleadoupdate = cuenta_empleadoRepository.findById(id).orElse(null);
        cuenta_empleadoupdate.setNom_usuario(ce.getNom_usuario());
        cuenta_empleadoupdate.setContraseña(ce.getContraseña());
        cuenta_empleadoupdate.setEmpleado(ce.getEmpleado());

        return cuenta_empleadoRepository.save(cuenta_empleadoupdate);
    }

    public List<Cuenta_EmpleadoDTO> findDTOList(){
        return cuenta_empleadomapper.toDTOlist(findAll());
    }

}