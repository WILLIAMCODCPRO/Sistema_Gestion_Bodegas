package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.request.ProductoRequestDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.service.impl.ProductoServiceimpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
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
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
public class ProductoController {

    private final ProductoServiceimpl productoServiceimpl;

    @Operation(summary = "Listar todos los productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos(){
        return ResponseEntity.ok().body(productoServiceimpl.listarProductos());
    }

    @Operation(summary = "Registrar un nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados en la petición", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> registrarProducto(
            @RequestBody @Valid ProductoRequestDTO productoRequestDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceimpl.registrarProducto(productoRequestDTO));
    }

    @Operation(summary = "Buscar un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarProductoID(@PathVariable Long id){
        return ResponseEntity.ok().body(productoServiceimpl.buscarProductoID(id));
    }

    @Operation(summary = "Actualizar un producto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody @Valid ProductoRequestDTO productoRequestDTO){

        return ResponseEntity.ok()
                .body(productoServiceimpl.actualizarProducto(productoRequestDTO,id));
    }

    @Operation(summary = "Eliminar un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoServiceimpl.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar productos con stock bajo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos con stock bajo obtenida correctamente")
    })
    @GetMapping("/stockbajo")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductosStockBajo(){
        return ResponseEntity.ok().body(productoServiceimpl.productosStockBajo());
    }
}
