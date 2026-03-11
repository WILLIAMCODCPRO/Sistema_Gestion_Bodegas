package com.s1.gestion_bodegas.dto.request;

import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record InventarioRequestDTO(

        Bodega bodega,

        Producto producto,

        Integer stock_producto
) {
}
