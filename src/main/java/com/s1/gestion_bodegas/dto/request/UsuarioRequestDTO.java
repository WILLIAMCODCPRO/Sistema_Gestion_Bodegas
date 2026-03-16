package com.s1.gestion_bodegas.dto.request;

import com.s1.gestion_bodegas.model.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record UsuarioRequestDTO(

        @Schema(description = "Nombre de usuario para iniciar sesión", example = "adminfdf01")
        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String nombreUsuario,

        @Schema(description = "Contraseña del usuario", example = "Admin1ergerg23*")
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password,

        @Schema(description = "Rol del usuario dentro del sistema", example = "ADMIN")
        @NotNull(message = "El rol no puede ser nulo")
        Rol rol

) {}
