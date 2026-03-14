package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.service.impl.MovimientoInventarioServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
@Validated
public class MovimientoInventarioController {
    public final MovimientoInventarioServiceimpl movimientoInventarioServiceimpl;

    @GetMapping
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> listarMovimientos(){
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.listarMovimientos());
    }
}
