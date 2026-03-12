package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.request.BodegaRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;

import java.util.List;

public interface BodegaService {
    List<BodegaResponseDTO> listarBodegas();

    BodegaResponseDTO registrarPersona(BodegaRequestDTO bodegaRequestDTO);
}
