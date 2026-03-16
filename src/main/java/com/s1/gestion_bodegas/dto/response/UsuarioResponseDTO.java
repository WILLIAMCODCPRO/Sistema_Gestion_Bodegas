package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.Rol;
import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioResponseDTO(

        @Schema(description = "ID único del usuario", example = "1")
        Long id,

        @Schema(description = "Nombre de usuario", example = "admin01")
        String nombreUsuario,

        @Schema(description = "Rol asignado al usuario", example = "ADMIN")
        Rol rol

) {}