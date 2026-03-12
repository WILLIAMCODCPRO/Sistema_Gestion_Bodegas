package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.InventarioResponseDTO;
import com.s1.gestion_bodegas.mapper.BodegaMapper;
import com.s1.gestion_bodegas.mapper.InventarioMapper;
import com.s1.gestion_bodegas.mapper.ProductoMapper;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.repository.InventarioRepository;
import com.s1.gestion_bodegas.repository.ProductoRepository;
import com.s1.gestion_bodegas.service.InventarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServicelmpl implements InventarioService {
    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;
    private final BodegaMapper bodegaMapper;
    private final ProductoMapper productoMapper;
    private  final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<InventarioResponseDTO> listarInventario() {
        return inventarioRepository.findAll()
                .stream()
                .map(dato -> inventarioMapper.entidadADTO(
                        dato,
                        bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodega().getId())
                                        .orElseThrow(() -> new EntityNotFoundException("No existe la bodega"))
                        ),
                        productoMapper.entidadADTO(
                                productoRepository.findById(dato.getProducto().getId())
                                        .orElseThrow(() -> new EntityNotFoundException("No existe el producto"))
                        )
                ))
                .toList();
    }

    public List<InventarioResponseDTO> listarStokcBajo(){
        return inventarioRepository.findByStockProductoLessThan(10)
                .stream()
                .map(dato -> inventarioMapper.entidadADTO(
                        dato,
                        bodegaMapper.entidadADTO(
                                bodegaRepository.findById(dato.getBodega().getId())
                                        .orElseThrow(() -> new EntityNotFoundException("No existe la bodega"))
                        ),
                        productoMapper.entidadADTO(
                                productoRepository.findById(dato.getProducto().getId())
                                        .orElseThrow(() -> new EntityNotFoundException("No existe el producto"))
                        )
                ))
                .toList();
    }
}
