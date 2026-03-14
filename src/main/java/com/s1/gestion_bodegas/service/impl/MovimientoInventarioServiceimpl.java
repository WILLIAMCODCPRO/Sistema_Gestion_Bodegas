package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.mapper.BodegaMapper;
import com.s1.gestion_bodegas.mapper.MovimientoInventarioMapper;
import com.s1.gestion_bodegas.mapper.ProductoMapper;
import com.s1.gestion_bodegas.mapper.UsuarioMapper;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.repository.MovimientoInventarioRepository;
import com.s1.gestion_bodegas.repository.ProductoRepository;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import com.s1.gestion_bodegas.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                                        .orElse(null)
                        )
                                : null,
                        dato.getBodegaDestino() != null
                                ? bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodegaDestino().getId())
                                        .orElse(null)
                        )
                                : null
                ))
                .toList();
    }
}
