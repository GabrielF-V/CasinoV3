package cl.duoc.clientes_casino.controller;

import cl.duoc.clientes_casino.dto.ClienteDTO;
import cl.duoc.clientes_casino.exception.ExceptionResponse;
import cl.duoc.clientes_casino.model.Cliente;
import cl.duoc.clientes_casino.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Clientes",
        description = "Operaciones disponibles para la gestion de clientes"

)
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
            summary = "Listar Clientes con los datos completos",
            description = "Obetiene el listado completo de los clientes registrados en el casino"
    )
    @ApiResponse(responseCode = "200", description = "Clientes listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Operation(
            summary = "Buscar Cliente por ID",
            description = "Obtiene un cliente específico según su id."
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @Operation(
            summary = "Crear Cliente",
            description = "Registra un nuevo Cliente en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Cliente creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente){
        Cliente newcliente = clienteService.save(cliente);
        return new ResponseEntity<>(newcliente, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Eliminar cliente",
            description = "Elimina un cliente existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Cliente eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Modificar Cliente por id",
            description = "Modifica un Cliente existente en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Cliente modificado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente clienteactualizado = clienteService.update(id, cliente);
        if(clienteactualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteactualizado);
    }


    @Operation(
            summary = "Listar Clientes",
            description = "Obtiene el listado completo de clientes registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Clientes listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(clienteService.findDTOList());
    }
}
