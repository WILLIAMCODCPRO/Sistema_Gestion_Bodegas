package com.s1.gestion_bodegas.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record BodegaResponseDTO(
        Long id,

        String nombre,

        String ubicacion,

        Integer capacidad,

        String encargado
) {
}
