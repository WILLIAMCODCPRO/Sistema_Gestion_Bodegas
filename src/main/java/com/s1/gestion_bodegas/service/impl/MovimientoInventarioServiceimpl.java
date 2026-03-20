package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.auth.AuthService;
import com.s1.gestion_bodegas.dto.request.MovimientoInventarioRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.mapper.BodegaMapper;
import com.s1.gestion_bodegas.mapper.MovimientoInventarioMapper;
import com.s1.gestion_bodegas.mapper.ProductoMapper;
import com.s1.gestion_bodegas.mapper.UsuarioMapper;
import com.s1.gestion_bodegas.model.*;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.repository.MovimientoInventarioRepository;
import com.s1.gestion_bodegas.repository.ProductoRepository;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import com.s1.gestion_bodegas.service.MovimientoInventarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioServiceimpl implements MovimientoInventarioService {

    public final MovimientoInventarioRepository movimientoInventarioRepository;
    public final MovimientoInventarioMapper movimientoInventarioMapper;
    public final UsuarioRepository usuarioRepository;
    public final UsuarioMapper usuarioMapper;
    public final BodegaRepository bodegaRepository;
    public final BodegaMapper bodegaMapper;
    public final ProductoServiceimpl productoServiceImpl;
    public final ProductoMapper productoMapper;
    public final ProductoRepository productoRepository;
    public final AuthService authService;

    @Override
    public List<MovimientoInventarioResponseDTO> listarMovimientos() {

        return movimientoInventarioRepository.findAll()
                .stream()
                .map(dato -> movimientoInventarioMapper.entidadADTO(
                        dato,
                        usuarioMapper.entidadADTO(
                                usuarioRepository.findById(dato.getUsuario().getId())
                                        .orElseThrow()
                        ),
                        productoServiceImpl.buscarProductoID(dato.getProducto().getId()),
                        dato.getBodegaOrigen() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaOrigen().getId())
                                        .orElseThrow()
                        )
                                : null,
                        dato.getBodegaDestino() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaDestino().getId())
                                        .orElseThrow()
                        )
                                : null
                ))
                .toList();
    }

    @Override
    public MovimientoInventarioResponseDTO registrarMovimiento(MovimientoInventarioRequestDTO movimientoInventarioRequestDTO) {
        Usuario usuario = authService.obtenerUsuarioAutenticado();
        Producto producto = productoRepository.findById(movimientoInventarioRequestDTO.productoID()).orElseThrow(()-> new EntityNotFoundException("Ese producto no existe para registrar"));
        BodegaResponseDTO bodegaProducto = bodegaMapper.entidadADTO(bodegaRepository.findById(producto.getBodega().getId()).orElseThrow());
        Bodega bodegaOrigen = movimientoInventarioRequestDTO.bodegaOrigenID() != null ? bodegaRepository.findById(movimientoInventarioRequestDTO.bodegaOrigenID()).orElseThrow(()-> new EntityNotFoundException("Esa bodega no existe para registrar")) : null;
        Bodega bodegaDestino = movimientoInventarioRequestDTO.bodegaDestinoID() != null ? bodegaRepository.findById(movimientoInventarioRequestDTO.bodegaDestinoID()).orElseThrow(()-> new EntityNotFoundException("Esa bodega no existe para registrar")) : null;
        MovimientoInventario movimientoInventario = movimientoInventarioMapper.DTOAEntidad(movimientoInventarioRequestDTO, usuario,producto,bodegaOrigen,bodegaDestino);
        return movimientoInventarioMapper.entidadADTO(movimientoInventarioRepository.save(movimientoInventario),usuarioMapper.entidadADTO(usuario),productoMapper.entidadADTO(producto,bodegaProducto),bodegaMapper.entidadADTO(bodegaOrigen),bodegaMapper.entidadADTO(bodegaDestino));
    }

    @Override
    public MovimientoInventarioResponseDTO buscarMovimientoID(Long id) {
        MovimientoInventario movimientoInventario = movimientoInventarioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No existe ese movimiento"));
        Usuario usuario = usuarioRepository.findById(movimientoInventario.getUsuario().getId()).orElseThrow();
        Producto producto = productoRepository.findById(movimientoInventario.getProducto().getId()).orElseThrow();
        BodegaResponseDTO bodegaProducto = bodegaMapper.entidadADTO(bodegaRepository.findById(producto.getBodega().getId()).orElseThrow());
        Bodega bodegaOrigen = movimientoInventario.getBodegaOrigen() != null ? bodegaRepository.findById(movimientoInventario.getBodegaOrigen().getId()).orElseThrow() : null;
        Bodega bodegaDestino = movimientoInventario.getBodegaDestino() != null ? bodegaRepository.findById(movimientoInventario.getBodegaDestino().getId()).orElseThrow() : null;
        return movimientoInventarioMapper.entidadADTO(movimientoInventario,usuarioMapper.entidadADTO(usuario),productoMapper.entidadADTO(producto,bodegaProducto),bodegaMapper.entidadADTO(bodegaOrigen), bodegaMapper.entidadADTO(bodegaDestino));
    }

    @Override
    public List<MovimientoInventarioResponseDTO> listarMovimientoRangoFecha(LocalDateTime fecha1, LocalDateTime fecha2) {
        return movimientoInventarioRepository.findByFechaBetween(fecha1,fecha2)
                .stream()
                .map(dato -> movimientoInventarioMapper.entidadADTO(
                        dato,
                        usuarioMapper.entidadADTO(
                                usuarioRepository.findById(dato.getUsuario().getId())
                                        .orElseThrow()
                        ),
                        productoServiceImpl.buscarProductoID(dato.getProducto().getId()),
                        dato.getBodegaOrigen() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaOrigen().getId())
                                        .orElseThrow()
                        )
                                : null,
                        dato.getBodegaDestino() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaDestino().getId())
                                        .orElseThrow()
                        )
                                : null
                ))
                .toList();
    }

    @Override
    public List<MovimientoInventarioResponseDTO> ultimosMovimientos() {
        return movimientoInventarioRepository.findTop10ByOrderByFechaDesc()
                .stream()
                .map(dato -> movimientoInventarioMapper.entidadADTO(
                        dato,
                        usuarioMapper.entidadADTO(
                                usuarioRepository.findById(dato.getUsuario().getId())
                                        .orElseThrow()
                        ),
                        productoServiceImpl.buscarProductoID(dato.getProducto().getId()),
                        dato.getBodegaOrigen() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaOrigen().getId())
                                        .orElseThrow()
                        )
                                : null,
                        dato.getBodegaDestino() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaDestino().getId())
                                        .orElseThrow()
                        )
                                : null
                ))
                .toList();
    }

    @Override
    public Long listarTipoMovimiento(TipoMovimiento tipoMovimiento) {
        return movimientoInventarioRepository.countByTipoMovimiento(tipoMovimiento);
    }

    @Override
    public Long movimeintosTotales() {
        return movimientoInventarioRepository.count();
    }


}
