package com.s1.gestion_bodegas.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record LoginRequest(

        @Schema(description = "Nombre de usuario registrado en el sistema", example = "admhhin01")
        String nombreUsuario,

        @Schema(description = "Contraseña del usuario", example = "Admin1hh23*")
        String password

) {}