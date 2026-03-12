package com.s1.gestion_bodegas.dto.response;


public record InventarioResponseDTO(

        Long id,

        BodegaResponseDTO bodega,

        ProductoResponseDTO producto,

        Integer stockProducto
) {
}
