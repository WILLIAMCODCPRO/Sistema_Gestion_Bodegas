package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.mapper.BodegaMapper;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.service.BodegaService;
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
}
