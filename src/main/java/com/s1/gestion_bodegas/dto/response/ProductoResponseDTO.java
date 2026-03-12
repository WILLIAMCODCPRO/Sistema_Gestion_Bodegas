package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.Bodega;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long id,

        String nombre,

        String categoria,

        BigDecimal precio,

        Integer stockProducto,

        BodegaResponseDTO bodega
) {
}
