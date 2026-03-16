package com.s1.gestion_bodegas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductoMasMovidoResponseDTO(

        @Schema(description = "ID del producto", example = "1")
        Long idProducto,

        @Schema(description = "Nombre del producto", example = "Camiseta")
        String nombreProducto,

        @Schema(description = "Total de movimientos realizados con este producto", example = "150")
        Long totalMovimientos

) {}