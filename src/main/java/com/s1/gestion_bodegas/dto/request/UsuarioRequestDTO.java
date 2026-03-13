package com.s1.gestion_bodegas.dto.request;

import com.s1.gestion_bodegas.model.Rol;

public record UsuarioRequestDTO(
        String nombreUsuario,
        String password,
        Rol rol
) {
}
