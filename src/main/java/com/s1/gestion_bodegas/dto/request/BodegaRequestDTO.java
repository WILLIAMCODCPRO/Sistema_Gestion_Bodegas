package com.s1.gestion_bodegas.dto.request;


public record BodegaRequestDTO(
        String nombre,

        String ubicacion,

        Integer capacidad,

        String encargado
) {
}
