package com.s1.gestion_bodegas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record BodegaResponseDTO(

        @Schema(description = "ID único de la bodega", example = "1")
        Long id,

        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        String nombre,

        @Schema(description = "Ubicación de la bodega", example = "Calle 123, Bogotá")
        String ubicacion,

        @Schema(description = "Capacidad máxima de la bodega", example = "5000")
        Integer capacidad,

        @Schema(description = "Nombre del encargado de la bodega", example = "Juan Pérez")
        String encargado

) {}
