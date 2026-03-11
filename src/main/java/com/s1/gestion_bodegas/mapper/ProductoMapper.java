package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.request.ProductoRequestDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoResponseDTO entidadADTO(Producto producto) {
        if (producto == null) return null;
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getPrecio()
        );
    }

    public Producto DTOAEntidad(ProductoRequestDTO productoRequestDTO) {
        if (productoRequestDTO == null) return null;
        Producto producto = new Producto();
        producto.setNombre(productoRequestDTO.nombre());
        producto.setCategoria(productoRequestDTO.categoria());
        producto.setPrecio(productoRequestDTO.precio());
        return producto;

    }

    public void actualizarEntidadDesdeDTO(ProductoRequestDTO productoRequestDTO, Producto producto){
        if (productoRequestDTO == null || producto == null) return;
        producto.setNombre(productoRequestDTO.nombre());
        producto.setCategoria(productoRequestDTO.categoria());
        producto.setPrecio(productoRequestDTO.precio());
    }
}
