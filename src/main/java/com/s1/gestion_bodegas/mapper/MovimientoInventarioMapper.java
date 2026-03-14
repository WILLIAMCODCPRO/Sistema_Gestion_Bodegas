package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.request.MovimientoInventarioRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.MovimientoInventario;
import com.s1.gestion_bodegas.model.Producto;
import com.s1.gestion_bodegas.model.Usuario;
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

    public MovimientoInventario DTOAEntidad(MovimientoInventarioRequestDTO movimientoInventarioRequestDTO, Usuario usuario, Producto producto, Bodega bodegaOrigen, Bodega bodegaDestino){
        if (movimientoInventarioRequestDTO == null || usuario == null || producto == null) return null;
        MovimientoInventario movimientoInventario = new MovimientoInventario();
        movimientoInventario.setUsuario(usuario);
        movimientoInventario.setTipoMovimiento(movimientoInventarioRequestDTO.tipoMovimiento());
        movimientoInventario.setProducto(producto);
        movimientoInventario.setCantidadProducto(movimientoInventarioRequestDTO.cantidadProducto());
        movimientoInventario.setBodegaOrigen(bodegaOrigen);
        movimientoInventario.setBodegaDestino(bodegaDestino);
        return movimientoInventario;
    }
}
