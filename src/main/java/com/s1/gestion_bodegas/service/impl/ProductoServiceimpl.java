package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.request.ProductoRequestDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.mapper.BodegaMapper;
import com.s1.gestion_bodegas.mapper.ProductoMapper;
import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.repository.ProductoRepository;
import com.s1.gestion_bodegas.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceimpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository
                .findAll()
                .stream()
                .map(dato -> productoMapper.entidadADTO(
                        dato,
                        bodegaMapper.entidadADTO(
                                bodegaRepository
                                        .findById(dato.getBodega().getId())
                                        .orElseThrow()
                        )
                ))
                .toList();
    }

    @Override
    public ProductoResponseDTO registrarProducto(ProductoRequestDTO productoRequestDTO) {
        Bodega bodega = bodegaRepository.findById(productoRequestDTO.bodegaId()).orElseThrow(()->new EntityNotFoundException("la bodega a insertar no existe"));
        Producto producto = productoMapper.DTOAEntidad(productoRequestDTO,bodega);
        return productoMapper.entidadADTO(productoRepository.save(producto),bodegaMapper.entidadADTO(bodega));
    }

    @Override
    public ProductoResponseDTO buscarProductoID(Long id) {
        return null;
    }

    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO productoRequestDTO, Long id) {
        return null;
    }

    @Override
    public void eliminarProducto(Long id) {

    }
}
