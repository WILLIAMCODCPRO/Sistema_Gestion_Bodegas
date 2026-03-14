package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.request.MovimientoInventarioRequestDTO;
import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.service.impl.MovimientoInventarioServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Validated
public class MovimientoInventarioController {
    public final MovimientoInventarioServiceimpl movimientoInventarioServiceimpl;

    @GetMapping
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> listarMovimientos(){
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.listarMovimientos());
    }

    @PostMapping
    public ResponseEntity<MovimientoInventarioResponseDTO> registrarMovimiento(@RequestBody MovimientoInventarioRequestDTO movimientoInventarioRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoInventarioServiceimpl.registrarMovimiento(movimientoInventarioRequestDTO));
    }
}
