package com.s1.gestion_bodegas.auth;

import com.s1.gestion_bodegas.dto.request.UsuarioRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Operaciones relacionadas con login y registro de usuarios")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login de usuario", description = "Permite iniciar sesión con nombre de usuario y contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Usuario o contraseña incorrectos", content = @Content)
    })
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Registro de nuevo usuario", description = "Permite crear un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Datos del usuario inválidos o mal estructurados", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Validated UsuarioRequestDTO usuarioRequestDTO) {
        authService.registrarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
