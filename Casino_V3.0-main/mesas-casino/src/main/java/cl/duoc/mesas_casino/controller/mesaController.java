package cl.duoc.mesas_casino.controller;

import cl.duoc.mesas_casino.dto.MesaDTO;
import cl.duoc.mesas_casino.exception.ExceptionResponse;
import cl.duoc.mesas_casino.model.Mesa;
import cl.duoc.mesas_casino.service.MesaService;
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
        name = "Mesas",
        description = "Operaciones disponibles para la gestión de mesas"
)
@RestController
@RequestMapping("api/v1/mesas")
public class mesaController {
    @Autowired
    private MesaService mesaService;

    @Operation(
            summary = "Listar mesas",
            description = "Obtiene el listado completo de mesas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Mesas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Mesa.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(mesaService.findAll());
    }

    @Operation(
            summary = "Buscar mesa por ID",
            description = "Obtiene una mesa específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Mesa encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mesa.class)))
    @ApiResponse(responseCode = "404", description = "Mesa no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Mesa mesa = mesaService.findById(id);
        if (mesa == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(mesa);
    }

    @Operation(
            summary = "Crear mesa",
            description = "Registra una nueva mesa en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Mesa creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mesa.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Mesa mesa){
        Mesa nueva = mesaService.save(mesa);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar mesa",
            description = "Elimina una mesa existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Mesa eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Mesa no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Crear mesa",
            description = "Registra una nueva mesa en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Mesa creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mesa.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Mesa no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Mesa mesa){
        Mesa mesaActualizada = mesaService.update(id, mesa);
        if ( mesaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mesaActualizada);
    }

    @Operation(
            summary = "Buscar mesa por ID",
            description = "Obtiene una mesa específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Mesa encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MesaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Mesa no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        MesaDTO mesa = mesaService.findDTO(id);
        if (mesa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mesa);
    }

    @Operation(
            summary = "Listar mesas",
            description = "Obtiene el listado completo de mesas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Mesas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MesaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(mesaService.findDTOList());
    }
}
