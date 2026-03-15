package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;

import java.util.List;

public interface AuditoriaService {
    List<AuditoriaResponseDTO> listarAuditorias();
}
