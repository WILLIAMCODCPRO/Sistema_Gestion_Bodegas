package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.service.impl.ProductoServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
@Validated
public class ProductoController {
    private final ProductoServiceimpl productoServiceimpl;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos(){
        return ResponseEntity.ok().body(productoServiceimpl.listarProductos());
    }
}
