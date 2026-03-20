package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.request.MovimientoInventarioRequestDTO;
import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.service.impl.MovimientoInventarioServiceimpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Movimientos de Inventario", description = "Operaciones relacionadas con los movimientos de inventario")
public class MovimientoInventarioController {

    private final MovimientoInventarioServiceimpl movimientoInventarioServiceimpl;

    @Operation(summary = "Listar todos los movimientos", description = "Obtiene una lista completa de todos los movimientos de inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> listarMovimientos() {
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.listarMovimientos());
    }

    @Operation(summary = "Registrar un movimiento de inventario", description = "Crea un nuevo movimiento de inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento registrado correctamente",
                    content = @Content(schema = @Schema(implementation = MovimientoInventarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o mal estructurados", content = @Content )
    })
    @PostMapping
    public ResponseEntity<MovimientoInventarioResponseDTO> registrarMovimiento(
            @RequestBody @Validated MovimientoInventarioRequestDTO movimientoInventarioRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimientoInventarioServiceimpl.registrarMovimiento(movimientoInventarioRequestDTO));
    }

    @Operation(summary = "Buscar movimiento por ID", description = "Obtiene un movimiento de inventario según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado",
                    content = @Content(schema = @Schema(implementation = MovimientoInventarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<MovimientoInventarioResponseDTO> buscarMovimientoID(@PathVariable Long id) {
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.buscarMovimientoID(id));
    }

    @Operation(summary = "Buscar movimientos por rango de fecha", description = "Obtiene los movimientos realizados entre dos fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimientos filtrados por fecha obtenidos correctamente"),
            @ApiResponse(responseCode = "400", description = "Fechas inválidas", content = @Content)
    })
    @GetMapping("/{fecha1}/{fecha2}")
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> buscarMovimientoRangoFecha(
            @PathVariable LocalDateTime fecha1,
            @PathVariable LocalDateTime fecha2) {
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.listarMovimientoRangoFecha(fecha1, fecha2));
    }


    @GetMapping("/movimientos/recientes")
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> listarRecientes(){
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.ultimosMovimientos());
    }
}