package com.s1.gestion_bodegas.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record BodegaRequestDTO(

        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        @NotBlank(message = "El nombre de la bodega es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(description = "Ubicación de la bodega", example = "Bogotá")
        @NotBlank(message = "La ubicación es obligatoria")
        @Size(min = 2, max = 100, message = "La ubicación debe tener entre 2 y 100 caracteres")
        String ubicacion,

        @Schema(description = "Capacidad máxima de almacenamiento", example = "500")
        @NotNull(message = "La capacidad es obligatoria")
        @Positive(message = "La capacidad debe ser mayor a 0")
        Integer capacidad,

        @Schema(description = "Nombre del encargado de la bodega", example = "Carlos Pérez")
        @NotBlank(message = "El nombre del encargado es obligatorio")
        @Size(min = 2, max = 60, message = "El encargado debe tener entre 2 y 60 caracteres")
        String encargado

) {}

