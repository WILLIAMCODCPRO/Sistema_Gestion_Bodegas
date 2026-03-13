package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;
import com.s1.gestion_bodegas.model.TipoMovimiento;
import com.s1.gestion_bodegas.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record MovimientoInventarioResponseDTO(
       Long id,

       LocalDateTime fecha,

       UsuarioResponseDTO usuario,

       TipoMovimiento tipoMovimiento,

       ProductoResponseDTO producto,

       Integer cantidadProducto,

       BodegaResponseDTO bodegaOrigen,

       BodegaResponseDTO bodegaDestino
) {
}
