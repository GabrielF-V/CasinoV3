package cl.duoc.cuentas_casino.controller;

import cl.duoc.cuentas_casino.dto.Cuenta_EmpleadoDTO;
import cl.duoc.cuentas_casino.exception.ExceptionResponse;
import cl.duoc.cuentas_casino.model.Cuenta_Empleado;
import cl.duoc.cuentas_casino.service.Cuenta_EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cuentas_empleados")
public class Cuenta_EmpleadoController {

    @Autowired
    private Cuenta_EmpleadoService cuentaEmpleadoService;

    @Operation(
            summary = "Listar cuenta de empleados",
            description = "Obtiene el listado completo de cuenta de empleados registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta de empleados listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cuenta_Empleado.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(cuentaEmpleadoService.findAll());
    }


    @Operation(
            summary = "Buscar cuenta de empleados por ID",
            description = "Obtiene una cuenta de empleados específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta De Empleados encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta_Empleado.class)))
    @ApiResponse(responseCode = "404", description = "Cuenta De Empleados no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Cuenta_Empleado cuenta_empleado = cuentaEmpleadoService.findById(id);
        if(cuenta_empleado == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuenta_empleado);
    }

    @Operation(
            summary = "Crear cuenta de empleados",
            description = "Registra una nueva cuenta de empleados en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Cuenta De Empleados creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta_Empleado.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registro(@RequestBody Cuenta_Empleado cuenta_empleado){
        Cuenta_Empleado nuevo = cuentaEmpleadoService.save(cuenta_empleado);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar cuenta de empleados",
            description = "Elimina una cuenta de empleados existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Cuenta De Empleados eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Cuenta De Empleados no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        cuentaEmpleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar cuenta de empleados por ID",
            description = "Obtiene una cuenta de empleados específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta De Empleados encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta_Empleado.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cuenta De Empleados no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Cuenta_Empleado cuenta_empleado){
        Cuenta_Empleado cuenta_empleadoupdate = cuentaEmpleadoService.update(id, cuenta_empleado);
        if(cuenta_empleadoupdate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta_empleadoupdate);
    }

    @Operation(
            summary = "Listar cuenta de empleados",
            description = "Obtiene el listado completo de cuenta de empleados registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta De Empleados listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cuenta_Empleado.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(cuentaEmpleadoService.findDTOList());
    }
}
