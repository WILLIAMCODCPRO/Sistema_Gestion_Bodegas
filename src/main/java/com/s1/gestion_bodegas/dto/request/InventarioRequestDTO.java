package com.s1.gestion_bodegas.dto.request;

import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;

public record InventarioRequestDTO(

        Bodega bodega,

        Producto producto,

        Integer stockProducto
) {
}
