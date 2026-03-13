package com.s1.gestion_bodegas.dto.request;

import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;
import com.s1.gestion_bodegas.model.TipoMovimiento;

public record MovimientoInventarioRequestDTO(
        TipoMovimiento tipoMovimiento,

        Long productoId,

        Integer cantidadProducto,

        Long bodegaOrigenId,

        Long bodegaDestinoId
) {
}
