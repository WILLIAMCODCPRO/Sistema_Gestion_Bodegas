package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.request.InventarioRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.InventarioResponseDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Inventario;
import com.s1.gestion_bodegas.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {


    public InventarioResponseDTO entidadADTO(Inventario inventario, BodegaResponseDTO bodegaResponseDTO, ProductoResponseDTO productoResponseDTO){
        if (inventario == null || bodegaResponseDTO == null || productoResponseDTO == null) return null;

        return new InventarioResponseDTO(
                inventario.getId(),
                bodegaResponseDTO,
                productoResponseDTO,
                inventario.getStockProducto()
        );
    }

    public Inventario DTOAEntidad(InventarioRequestDTO inventarioRequestDTO, Bodega bodega, Producto producto){
        if (inventarioRequestDTO == null || bodega == null || producto == null) return null;
        Inventario inventario = new Inventario();
        inventario.setBodega(bodega);
        inventario.setProducto(producto);
        inventario.setStockProducto(inventarioRequestDTO.stockProducto());
        return inventario;
    }

    public void actualizarEntidadDesdeDTO(InventarioRequestDTO inventarioRequestDTO, Inventario inventario, Bodega bodega, Producto producto){
        inventario.setBodega(bodega);
        inventario.setProducto(producto);
        inventario.setStockProducto(inventarioRequestDTO.stockProducto());
    }
}
