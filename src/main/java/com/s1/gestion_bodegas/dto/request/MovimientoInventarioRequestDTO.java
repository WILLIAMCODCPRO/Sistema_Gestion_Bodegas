package com.s1.gestion_bodegas.dto.request;

import com.s1.gestion_bodegas.model.TipoMovimiento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record MovimientoInventarioRequestDTO(

        @Schema(description = "Tipo de movimiento de inventario (ENTRADA, SALIDA o TRANSFERENCIA)", example = "ENTRADA")
        @NotNull(message = "El tipo de movimiento no puede ser nulo")
        TipoMovimiento tipoMovimiento,

        @Schema(description = "ID del producto involucrado en el movimiento", example = "1")
        @NotNull(message = "El ID del producto no puede ser nulo")
        Long productoID,

        @Schema(description = "Cantidad de producto que se moverá", example = "50")
        @NotNull(message = "La cantidad de producto no puede ser nula")
        @Positive(message = "La cantidad debe ser mayor a 0")
        Integer cantidadProducto,

        @Schema(description = "ID de la bodega de origen (necesario para SALIDA o TRANSFERENCIA)", example = "1")
        Long bodegaOrigenID,

        @Schema(description = "ID de la bodega destino (necesario para ENTRADA o TRANSFERENCIA)", example = "2")
        Long bodegaDestinoID

) {}

