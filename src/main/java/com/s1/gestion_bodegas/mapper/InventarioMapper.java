package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.response.InventarioResponseDTO;
import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Inventario;
import com.s1.gestion_bodegas.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {
    public InventarioResponseDTO entidadADTO(Inventario inventario, Producto producto, Bodega bodega){
        if (inventario == null || producto == null || bodega == null) return null;
    }
}
