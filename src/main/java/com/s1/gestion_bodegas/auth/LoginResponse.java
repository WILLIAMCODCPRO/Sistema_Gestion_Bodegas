package com.s1.gestion_bodegas.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(

        @Schema(description = "Token JWT generado al iniciar sesión", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token

) {}