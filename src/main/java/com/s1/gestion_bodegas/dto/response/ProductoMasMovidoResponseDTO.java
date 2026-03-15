package com.s1.gestion_bodegas.dto.response;

public record ProductoMasMovidoResponseDTO(
        Long idProducto,
        String nombreProducto,
        Long totalMovimientos
) {
}
