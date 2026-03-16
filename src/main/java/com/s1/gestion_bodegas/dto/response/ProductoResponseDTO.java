package com.s1.gestion_bodegas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductoResponseDTO(

        @Schema(description = "ID único del producto", example = "1")
        Long id,

        @Schema(description = "Nombre del producto", example = "Camiseta")
        String nombre,

        @Schema(description = "Categoría del producto", example = "Ropa")
        String categoria,

        @Schema(description = "Precio del producto", example = "99.99")
        BigDecimal precio,

        @Schema(description = "Stock disponible del producto", example = "100")
        Integer stockProducto,

        @Schema(description = "Bodega a la que pertenece el producto")
        BodegaResponseDTO bodega

) {}
