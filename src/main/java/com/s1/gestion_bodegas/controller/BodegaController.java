package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.request.BodegaRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.service.impl.BodegaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodega")
@RequiredArgsConstructor
@Validated
@Tag(name = "Bodegas", description = "Operaciones relacionadas con la gestión de bodegas")
public class BodegaController {

    private final BodegaServiceImpl bodegaService;

    @Operation(summary = "Listar todas las bodegas", description = "Obtiene una lista completa de todas las bodegas registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de bodegas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<BodegaResponseDTO>> listarBodegas() {
        return ResponseEntity.ok().body(bodegaService.listarBodegas());
    }

    @Operation(summary = "Registrar una nueva bodega", description = "Crea una nueva bodega con los datos enviados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bodega creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la bodega inválidos o mal estructurados", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BodegaResponseDTO> registrarBodega(@RequestBody @Validated BodegaRequestDTO bodegaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.registrarBodega(bodegaRequestDTO));
    }

    @Operation(summary = "Buscar una bodega por ID", description = "Obtiene los datos de una bodega específica según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodega encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró ninguna bodega con el ID proporcionado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> buscarBodegaID(@PathVariable Long id) {
        return ResponseEntity.ok().body(bodegaService.buscarBodegaID(id));
    }

    @Operation(summary = "Actualizar una bodega", description = "Actualiza los datos de una bodega existente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodega actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la bodega inválidos o mal estructurados", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontró ninguna bodega con el ID proporcionado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> actualizarBodega(@PathVariable Long id, @RequestBody @Validated BodegaRequestDTO bodegaRequestDTO) {
        return ResponseEntity.ok().body(bodegaService.actualizarBodega(bodegaRequestDTO, id));
    }

    @Operation(summary = "Eliminar una bodega", description = "Elimina una bodega existente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bodega eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró ninguna bodega con el ID proporcionado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBodega(@PathVariable Long id) {
        bodegaService.eliminarBodega(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
