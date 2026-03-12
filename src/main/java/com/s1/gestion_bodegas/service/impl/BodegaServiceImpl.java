package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.request.BodegaRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.mapper.BodegaMapper;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.service.BodegaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {
    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    @Override
    public List<BodegaResponseDTO> listarBodegas() {
        return bodegaRepository.findAll().stream().map(bodegaMapper::entidadADTO).toList();
    }

    @Override
    public BodegaResponseDTO registrarBodega(BodegaRequestDTO bodegaRequestDTO) {
        return bodegaMapper.entidadADTO(bodegaRepository.save(bodegaMapper.DTOAEntidad(bodegaRequestDTO)));
    }

    @Override
    public BodegaResponseDTO buscarBodegaID(Long id) {
        return bodegaMapper.entidadADTO(bodegaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No existe dicha bodega")));
    }
}
