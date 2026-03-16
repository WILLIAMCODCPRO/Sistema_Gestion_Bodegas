package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.TipoOperacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AuditoriaResponseDTO(

        @Schema(description = "ID único de la auditoría", example = "1")
        Long id,

        @Schema(description = "Tipo de operación realizada", example = "CREACION")
        TipoOperacion tipoOperacion,

        @Schema(description = "Fecha y hora en que se realizó la operación", example = "2026-03-16T12:00:00")
        LocalDateTime fecha,

        @Schema(description = "Usuario que realizó la operación")
        UsuarioResponseDTO usuario,

        @Schema(description = "Entidad afectada por la operación", example = "Producto")
        String entidad,

        @Schema(description = "Valor antiguo de la entidad antes de la operación", example = "{\"nombre\":\"Camiseta\",\"stock\":50}")
        String valorAntiguo,

        @Schema(description = "Valor nuevo de la entidad después de la operación", example = "{\"nombre\":\"Camiseta\",\"stock\":100}")
        String valorNuevo

) {}
