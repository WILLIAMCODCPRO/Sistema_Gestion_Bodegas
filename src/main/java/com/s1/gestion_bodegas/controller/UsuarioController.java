package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.service.impl.UsuarioServiceImpl;
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

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
@Validated
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios del sistema")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;

    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista completa de todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioServiceImpl.listarUsuarios());
    }
}
