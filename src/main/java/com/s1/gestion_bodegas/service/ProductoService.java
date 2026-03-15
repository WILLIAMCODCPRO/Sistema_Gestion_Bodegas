package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.request.ProductoRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> listarProductos();

    ProductoResponseDTO registrarProducto(ProductoRequestDTO productoRequestDTO);

    ProductoResponseDTO buscarProductoID(Long id);

    ProductoResponseDTO actualizarProducto(ProductoRequestDTO productoRequestDTO, Long id);

    void eliminarProducto(Long id);

    List<ProductoResponseDTO> productosStockBajo();
}
