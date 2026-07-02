package cl.duoc.cuentas_casino.controller;

import cl.duoc.cuentas_casino.dto.Cuenta_ClienteDTO;
import cl.duoc.cuentas_casino.exception.ExceptionResponse;
import cl.duoc.cuentas_casino.model.Cuenta_Cliente;
import cl.duoc.cuentas_casino.service.Cuenta_ClienteService;
import feign.Response;
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
        name = "Cuentas Clientes",
        description = "Operaciones disponibles para la gestión de cuentas de los clientes"
)
@RestController
@RequestMapping("/api/v1/cuentas_clientes")
public class Cuenta_ClienteController {

    @Autowired
    private Cuenta_ClienteService cuentaClienteService;

    @Operation(
            summary = "Listar tutorías",
            description = "Obtiene el listado completo de las cuentas de los clientes registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Cuentas de los clientes listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cuenta_Cliente.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(cuentaClienteService.findAll());
    }

    @Operation(
            summary = "Buscar la cuenta de un cliente por ID",
            description = "Obtiene una cuenta de un cliente específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta de cliente encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta_Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cuenta de cliente no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Cuenta_Cliente cuenta_cliente = cuentaClienteService.findById(id);
        if(cuenta_cliente == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuenta_cliente);
    }

    @Operation(
            summary = "Crear la cuenta de cliente",
            description = "Registra una nueva cuenta para el cliente en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Cuenta del cliente creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta_Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registro(@RequestBody Cuenta_Cliente cuenta_cliente){
        Cuenta_Cliente nuevo = cuentaClienteService.save(cuenta_cliente);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar la cuenta del cliente",
            description = "Elimina una cuenta de un cliente existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Cuenta del cliente eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Cuenta del cliente no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        cuentaClienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualiza la cuenta de un cliente",
            description = "Actualiza la cuenta de un cliente existente según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta del cliente encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta_Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cuenta de cliente no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Cuenta_Cliente cuenta_cliente){
        Cuenta_Cliente cuenta_clienteupdate = cuentaClienteService.update(id, cuenta_cliente);
        if(cuenta_clienteupdate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta_clienteupdate);
    }

    @Operation(
            summary = "Listar Cuentas con datos especificos",
            description = "Obtiene el listado con datos especificos de las cuentas de los clientes registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Cuenta de cliente listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cuenta_Cliente.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(cuentaClienteService.findDTOList());
    }
}
