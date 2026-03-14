package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.model.MovimientoInventario;
import org.springframework.stereotype.Component;

@Component
public class MovimientoInventarioMapper {
    public MovimientoInventarioResponseDTO entidadADTO(MovimientoInventario movimientoInventario, UsuarioResponseDTO usuarioResponseDTO, ProductoResponseDTO productoResponseDTO, BodegaResponseDTO bodegaResponseDTO, BodegaResponseDTO bodegaResponseDTO2){
        if (movimientoInventario == null || usuarioResponseDTO == null || productoResponseDTO == null) return null;
        return new MovimientoInventarioResponseDTO(
                movimientoInventario.getId(),
                movimientoInventario.getFecha(),
                usuarioResponseDTO,
                movimientoInventario.getTipoMovimiento(),
                productoResponseDTO,
                movimientoInventario.getCantidadProducto(),
                bodegaResponseDTO,
                bodegaResponseDTO2
        );
    }
}
