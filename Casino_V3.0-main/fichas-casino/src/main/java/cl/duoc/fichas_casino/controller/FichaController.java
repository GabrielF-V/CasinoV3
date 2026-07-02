package cl.duoc.fichas_casino.controller;

import cl.duoc.fichas_casino.dto.FichaDTO;
import cl.duoc.fichas_casino.exception.ExceptionResponse;
import cl.duoc.fichas_casino.model.Ficha;

import cl.duoc.fichas_casino.service.FichaService;

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
        name = "Fichas",
        description = "Operaciones disponibles para la gestión de fichas"
)
@RestController
@RequestMapping("api/v1/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @Operation(
            summary = "Listar fichas",
            description = "Obtiene el listado completo de fichas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Fichas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Ficha.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(fichaService.findAll());
    }

    @Operation(
            summary = "Buscar ficha por ID",
            description = "Obtiene una ficha específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Ficha encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ficha.class)))
    @ApiResponse(responseCode = "404", description = "Ficha no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorNro(@PathVariable Long nro_ficha){
        Ficha ficha = fichaService.findByNro(nro_ficha);
        if(ficha == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ficha);
    }

    @Operation(
            summary = "Crear ficha",
            description = "Registra una nueva ficha en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Ficha creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ficha.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Ficha ficha){
        Ficha nuevo = fichaService.save(ficha);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar ficha",
            description = "Elimina una ficha existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Ficha eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Ficha no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @DeleteMapping
    public ResponseEntity<?> borrar(@PathVariable Long nro_ficha){
        fichaService.delete(nro_ficha);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar ficha por ID",
            description = "Obtiene una ficha específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Ficha encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FichaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Ficha no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        FichaDTO ficha = fichaService.findDTO(id);
        if (ficha == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ficha);
    }

    @Operation(
            summary = "Listar fichas de manera ordana y con informacion compacta",
            description = "Obtiene el listado completo de fichas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Fichas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FichaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(fichaService.findDTOList());
    }
}
