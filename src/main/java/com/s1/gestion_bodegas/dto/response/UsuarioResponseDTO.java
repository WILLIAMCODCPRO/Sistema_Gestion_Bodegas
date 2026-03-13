package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.Rol;

public record UsuarioResponseDTO(
        Long id,
        String nombreUsuario,
        Rol rol
) {
}
