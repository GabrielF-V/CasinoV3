package cl.duoc.deudores_casino.controller;

import cl.duoc.deudores_casino.dto.DeudorDTO;
import cl.duoc.deudores_casino.exception.ExceptionResponse;
import cl.duoc.deudores_casino.model.Deudor;
import cl.duoc.deudores_casino.service.DeudorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Deudores",
        description = "Operaciones disponibles para la gestión de deudores"
)
@RestController
@RequestMapping("api/v1/deudores")
public class DeudorController {
    @Autowired
    private DeudorService deudorService;

    @Operation(
            summary = "Listar deudores",
            description = "Obtiene el listado completo de deudores registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Deudores listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Deudor.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(deudorService.findAll());
    }

    @Operation(
            summary = "Buscar deudor por ID",
            description = "Obtiene un deudor específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Deudor encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Deudor.class)))
    @ApiResponse(responseCode = "404", description = "Deudor no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id_deudor}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id_deudor){
        Deudor deudor = deudorService.findById(id_deudor);
        if(deudor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deudor);
    }

    @Operation(
            summary = "Crear deudor",
            description = "Registra un nuevo deudor en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Deudor creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Deudor.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Deudor deudor){
        Deudor deudorNueva = deudorService.save(deudor);
        return new ResponseEntity<>(deudorNueva, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar deudore",
            description = "Actualiza un deudor que este en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Deudor actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Deudor.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Deudor no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id_deudor}")
    public ResponseEntity<?> actualizar(@PathVariable Long id_deudor, @Valid @RequestBody Deudor deudor){
        Deudor deudorActualizada = deudorService.update(id_deudor,deudor);
        if (deudorActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deudorActualizada);
    }

    @Operation(
            summary = "Eliminar deudor",
            description = "Elimina un deudor existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Deudor eliminado correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Deudor no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id_deudor}")
    public ResponseEntity<?> borrar(@PathVariable Long id_deudor){
        deudorService.delete(id_deudor);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar deudor para ver datos especificos por ID",
            description = "Obtiene un deudor específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Deudor encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeudorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Deudor no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        DeudorDTO deudor = deudorService.findDTO(id);
        if (deudor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deudor);
    }
    
    @Operation(
            summary = "Listar deudores y ver datos especificos",
            description = "Obtiene el listado completo de deudores registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Deudores listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DeudorDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(deudorService.findDTOList());
    }
    
}
