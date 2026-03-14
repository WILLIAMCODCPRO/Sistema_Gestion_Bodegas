package com.s1.gestion_bodegas.dto.request;


import com.s1.gestion_bodegas.model.TipoMovimiento;

public record MovimientoInventarioRequestDTO(
        TipoMovimiento tipoMovimiento,

        Long productoID,

        Integer cantidadProducto,

        Long bodegaOrigenID,

        Long bodegaDestinoID
) {
}
