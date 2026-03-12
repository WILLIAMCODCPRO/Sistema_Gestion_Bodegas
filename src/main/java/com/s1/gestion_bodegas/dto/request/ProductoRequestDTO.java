package com.s1.gestion_bodegas.dto.request;

import java.math.BigDecimal;

public record ProductoRequestDTO(

        String nombre,

        String categoria,

        BigDecimal precio,

        Integer stockProducto,

        Long bodegaId
) {
}
