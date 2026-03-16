package com.s1.gestion_bodegas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record StockBodegaResponseDTO(

        @Schema(description = "ID de la bodega", example = "1")
        Long idBodega,

        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        String nombreBodega,

        @Schema(description = "Stock total de productos en la bodega", example = "5000")
        Long stockTotal

) {}