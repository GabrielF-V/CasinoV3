package cl.duoc.empleado_casino.controller;

import cl.duoc.empleado_casino.dto.EmpleadoDTO;
import cl.duoc.empleado_casino.exception.ExceptionResponse;
import cl.duoc.empleado_casino.model.Empleado;
import cl.duoc.empleado_casino.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Empleados",
        description = "Operaciones disponibles para la gestión de empleados"
)
@RestController
@RequestMapping("api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Operation(
            summary = "Listar empleados",
            description = "Obtiene el listado completo de empleados registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Empleados listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Empleado.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @Operation(
            summary = "Buscar empleado por ID",
            description = "Obtiene una empleado específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Empleado encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class)))
    @ApiResponse(responseCode = "404", description = "Empleado no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Empleado empleado = empleadoService.findById(id);
        if(empleado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
    }

    @Operation(
            summary = "Crear empleado",
            description = "Registra una nueva empleado en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Empleado creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Empleado empleado){
        Empleado nuevo = empleadoService.save(empleado);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar empleado",
            description = "Elimina una empleado existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Empleado eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Empleado no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar empleado",
            description = "Actualiza un empleado en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Empleado Actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empleado empleado){
        Empleado empleadoactualizar = empleadoService.update(id, empleado);
        if(empleadoactualizar == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoactualizar);
    }

    @Operation(
            summary = "Listar empleados con sus datos limitados",
            description = "Obtiene el listado de empleados registrados en el sistema de manera ordenada."
    )
    @ApiResponse(responseCode = "200", description = "Empleados listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmpleadoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(empleadoService.findDTOList());
    }


}
