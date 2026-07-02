package cl.duoc.reportes_service.controller;

import cl.duoc.reportes_service.clients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reportes")
public class ReporteController {

    @Autowired
    private ClientesFeign clientesFeign;

    @Autowired
    private EmpleadoFeign empleadoFeign;
    @Autowired
    private Cuenta_EmpleadoFeign cuenta_EmpleadoFeign;
    @Autowired
    private Cuenta_ClienteFeign cuenta_ClienteFeign;

    @Autowired
    private FichaFeign fichaFeign;
    @Autowired
    private JuegoFeign juegoFeign;
    @Autowired
    private MaquinaFeign maquinaFeign;
    @Autowired
    private MesaFeign mesaFeign;

    @Autowired
    private DeudorFeign deudorFeign;

    @GetMapping("/listar-cliente")
    public ResponseEntity<?> listarClientes(){
        return ResponseEntity.ok(clientesFeign.listadoEstudiante());
    }

    @GetMapping("/listar-empleados")
    public ResponseEntity<?> listarEmpleados(){
        return ResponseEntity.ok(empleadoFeign.listadoEmpleado());
    }

    @GetMapping("/listar-cuentas_empleados")
    public ResponseEntity<?> listarCuenta_Empleado(){
        return ResponseEntity.ok(cuenta_EmpleadoFeign.listadoCuenta_Empleado());
    }

    @GetMapping("/listar-cuentas_clientes")
    public ResponseEntity<?> listarCuenta_Cliente(){
        return ResponseEntity.ok(cuenta_ClienteFeign.listadoCuenta_Cliente());
    }

    @GetMapping("/listar-fichas")
    public ResponseEntity<?> listarFichas(){
        return ResponseEntity.ok(fichaFeign.listadoFichas());
    }

    @GetMapping("/listar-juegos")
    public ResponseEntity<?> listarJuego(){
        return ResponseEntity.ok(juegoFeign.listadoJuegos());
    }

    @GetMapping("/listar-maquinas")
    public ResponseEntity<?> listarMaquina(){
        return ResponseEntity.ok(maquinaFeign.listadoMaquina());
    }

    @GetMapping("/listar-mesas")
    public ResponseEntity<?> listarMesa(){
        return ResponseEntity.ok(mesaFeign.listadoMesas());
    }

    @GetMapping("/listar-deudores")
    public ResponseEntity<?> listarDeudores(){
        return ResponseEntity.ok(deudorFeign.listadoDeudor());
    }
}
