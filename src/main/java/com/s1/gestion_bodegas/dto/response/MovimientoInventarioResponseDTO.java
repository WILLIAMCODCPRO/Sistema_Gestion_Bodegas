package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.TipoMovimiento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MovimientoInventarioResponseDTO(

        @Schema(description = "ID único del movimiento", example = "1")
        Long id,

        @Schema(description = "Fecha y hora del movimiento", example = "2026-03-16T12:00:00")
        LocalDateTime fecha,

        @Schema(description = "Usuario que realizó el movimiento")
        UsuarioResponseDTO usuario,

        @Schema(description = "Tipo de movimiento realizado", example = "ENTRADA")
        TipoMovimiento tipoMovimiento,

        @Schema(description = "Producto involucrado en el movimiento")
        ProductoResponseDTO producto,

        @Schema(description = "Cantidad de producto movida", example = "50")
        Integer cantidadProducto,

        @Schema(description = "Bodega de origen del movimiento")
        BodegaResponseDTO bodegaOrigen,

        @Schema(description = "Bodega de destino del movimiento (si aplica)")
        BodegaResponseDTO bodegaDestino

) {}