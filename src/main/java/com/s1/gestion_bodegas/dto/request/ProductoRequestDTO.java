package com.s1.gestion_bodegas.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductoRequestDTO(

        @Schema(description = "Nombre del producto", example = "Camiseta")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(description = "Categoría del producto", example = "Ropa")
        @NotBlank(message = "La categoría no puede estar vacía")
        @Size(min = 2, max = 30, message = "La categoría debe tener entre 2 y 30 caracteres")
        String categoria,

        @Schema(description = "Precio del producto", example = "99.99")
        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        BigDecimal precio,

        @Schema(description = "Stock disponible", example = "100")
        @NotNull(message = "El stock no puede ser nulo")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stockProducto,

        @Schema(description = "ID de la bodega", example = "1")
        @NotNull(message = "El ID de la bodega no puede ser nulo")
        Long bodegaId

) {}
