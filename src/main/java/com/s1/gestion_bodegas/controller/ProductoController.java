package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.request.ProductoRequestDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.service.impl.ProductoServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ProductoResponseDTO>registrarProducto(@RequestBody ProductoRequestDTO productoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServiceimpl.registrarProducto(productoRequestDTO));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductoResponseDTO>buscarProductoID(@PathVariable Long id){
        return ResponseEntity.ok().body(productoServiceimpl.buscarProductoID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO>actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO productoRequestDTO){
        return ResponseEntity.ok().body(productoServiceimpl.actualizarProducto(productoRequestDTO,id));
    }
}
