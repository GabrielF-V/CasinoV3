package cl.duoc.maquinas_casino.controller;

import cl.duoc.maquinas_casino.dto.MaquinaDTO;
import cl.duoc.maquinas_casino.exception.ExceptionResponse;
import cl.duoc.maquinas_casino.model.Maquina;
import cl.duoc.maquinas_casino.service.MaquinaService;
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
        name = "Maquinas",
        description = "Operaciones disponibles para la gestión de maquinas"
)
@RestController
@RequestMapping("api/v1/maquinas")

public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;

    @Operation(
            summary = "Listar maquinas",
            description = "Obtiene el listado completo de maquinas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Maquinas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Maquina.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(maquinaService.findAll());
    }

    @Operation(
            summary = "Buscar maquina por ID",
            description = "Obtiene una maquina específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Maquina encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maquina.class)))
    @ApiResponse(responseCode = "404", description = "Maquina no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id_maquina}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id_maquina){
        Maquina maquina = maquinaService.findById(id_maquina);
        if(maquina == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maquina);
    }

    @Operation(
            summary = "Crear maquina",
            description = "Registra una nueva maquina en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Maquina creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maquina.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Maquina maquina){
        Maquina maquinaNueva = maquinaService.save(maquina);
        return new ResponseEntity<>(maquinaNueva, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Crear maquina",
            description = "Registra una nueva maquina en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Maquina creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maquina.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Maquina no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PutMapping("/{id_maquina}")
    public ResponseEntity<?> actualizar(@PathVariable Long id_maquina, @Valid @RequestBody Maquina maquina){
        Maquina maquinaActualizada = maquinaService.update(id_maquina,maquina);
        if (maquinaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(maquinaActualizada);
    }

    @Operation(
            summary = "Eliminar maquina",
            description = "Elimina una maquina existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Maquina eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Maquina no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping("/{id_maquina}")
    public ResponseEntity<?> borrar(@PathVariable Long id_maquina){
        maquinaService.delete(id_maquina);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar maquina por ID",
            description = "Obtiene una maquina específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Maquina encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maquina.class)))
    @ApiResponse(responseCode = "404", description = "Maquina no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        MaquinaDTO maquina = maquinaService.findDTO(id);
        if (maquina == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(maquina);
    }

    @Operation(
            summary = "Listar maquinas con datos especificos",
            description = "Obtiene el listado completo de maquinas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Maquinas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MaquinaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(maquinaService.findDTOList());
    }
}
