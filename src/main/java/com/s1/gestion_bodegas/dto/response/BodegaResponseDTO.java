package com.s1.gestion_bodegas.dto.response;


public record BodegaResponseDTO(
        Long id,

        String nombre,

        String ubicacion,

        Integer capacidad,

        String encargado
) {
}
