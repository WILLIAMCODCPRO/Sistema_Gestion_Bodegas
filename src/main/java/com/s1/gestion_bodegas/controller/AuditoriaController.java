package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;
import com.s1.gestion_bodegas.service.impl.AuditoriaServiceimpl;
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
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auditoría", description = "Operaciones relacionadas con el historial de auditorías")
public class AuditoriaController {

    private final AuditoriaServiceimpl auditoriaServiceimpl;

    @Operation(summary = "Listar todas las auditorías", description = "Obtiene la lista completa de auditorías del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de auditorías obtenida correctamente",
                    content = @Content(schema = @Schema(implementation = AuditoriaResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDTO>> listarAuditoria() {
        return ResponseEntity.ok().body(auditoriaServiceimpl.listarAuditorias());
    }

    @Operation(summary = "Buscar auditoría por ID", description = "Obtiene los datos de una auditoría específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditoría encontrada",
                    content = @Content(schema = @Schema(implementation = AuditoriaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Auditoría no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDTO> buscarAuditoriaID(@PathVariable Long id) {
        return ResponseEntity.ok().body(auditoriaServiceimpl.listarAuditoriaID(id));
    }

    @Operation(summary = "Listar auditorías de un usuario", description = "Obtiene todas las auditorías realizadas por un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditorías del usuario obtenidas correctamente",
                    content = @Content(schema = @Schema(implementation = AuditoriaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o sin auditorías", content = @Content)
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<AuditoriaResponseDTO>> listarAuditoriaUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok().body(auditoriaServiceimpl.auditoriaUsuario(idUsuario));
    }
}
