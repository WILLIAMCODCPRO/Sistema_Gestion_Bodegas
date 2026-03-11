package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;

public record InventarioResponseDTO(

        Long id,

        Bodega bodega,

        Producto producto,

        Integer stock_producto
) {
}
