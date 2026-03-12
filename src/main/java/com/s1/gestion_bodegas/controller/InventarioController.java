package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.InventarioResponseDTO;
import com.s1.gestion_bodegas.service.InventarioService;
import com.s1.gestion_bodegas.service.impl.InventarioServicelmpl;
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
public class InventarioController {
    private final InventarioServicelmpl inventarioServicelmpl;

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listarInventario(){
        return ResponseEntity.ok().body(inventarioServicelmpl.listarInventario());
    }

    @GetMapping("/stockbajo")
    public ResponseEntity<List<InventarioResponseDTO>> listarStockBajo(){
        return ResponseEntity.ok().body(inventarioServicelmpl.listarStokcBajo());
    }
}
