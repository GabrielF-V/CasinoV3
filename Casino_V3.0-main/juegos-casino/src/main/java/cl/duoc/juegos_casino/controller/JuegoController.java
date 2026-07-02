package cl.duoc.juegos_casino.controller;

import cl.duoc.juegos_casino.dto.JuegoDTO;
import cl.duoc.juegos_casino.exception.ExceptionResponse;
import cl.duoc.juegos_casino.model.Juego;
import cl.duoc.juegos_casino.service.JuegoService;
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
        name = "Juegos",
        description = "Operaciones disponibles para la gestión de juegos"
)
@RestController
@RequestMapping("api/v1/juegos")

public class JuegoController {
    @Autowired
    private JuegoService juegoService;

    @Operation(
            summary = "Listar juegos",
            description = "Obtiene el listado completo de juegos registrados en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Juegos listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Juego.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(juegoService.findAll());
    }

    @Operation(
            summary = "Buscar juego por ID",
            description = "Obtiene una juego específico según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Juego encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Juego.class)))
    @ApiResponse(responseCode = "404", description = "Juego no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Juego juego = juegoService.findById(id);
        if(juego == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(juego);
    }

    @Operation(
            summary = "Crear juego",
            description = "Registrar un juego en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Juego creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Juego.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Juego juego){
        Juego juegoNuevo = juegoService.save(juego);
        return new ResponseEntity<>(juegoNuevo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar juego",
            description = "actualiza un juego en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Juego actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Juego.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Juego no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Juego juego){
        Juego juegoActualizado = juegoService.update(id,juego);
        if (juegoActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(juegoActualizado);
    }

    @Operation(
            summary = "Eliminar juego",
            description = "Elimina un juego existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Juego eliminado correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Juego no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        juegoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar juego por ID",
            description = "Obtiene un juego específico según su identificador con datos especificos."
    )
    @ApiResponse(responseCode = "200", description = "Juego encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JuegoDTO.class)))
    @ApiResponse(responseCode = "404", description = "Juego no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        JuegoDTO juego = juegoService.findDTO(id);
        if (juego == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(juego);
    }

    @Operation(
            summary = "Listar juegos",
            description = "Obtiene el listado completo de juegos del sistema con datos especificos."
    )
    @ApiResponse(responseCode = "200", description = "Juegos listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JuegoDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(juegoService.findDTOList());
    }
}
