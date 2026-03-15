package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;
import com.s1.gestion_bodegas.service.impl.AuditoriaServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
@Validated
public class AuditoriaController {

    public final AuditoriaServiceimpl auditoriaServiceimpl;

    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDTO>> listarAuditoria(){
        return ResponseEntity.ok().body(auditoriaServiceimpl.listarAuditorias());
    }
}
