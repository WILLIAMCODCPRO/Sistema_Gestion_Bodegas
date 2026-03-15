package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;
import com.s1.gestion_bodegas.model.Usuario;

import java.util.List;

public interface AuditoriaService {
    List<AuditoriaResponseDTO> listarAuditorias();

    AuditoriaResponseDTO listarAuditoriaID(Long id);

    List<AuditoriaResponseDTO> auditoriaUsuario(Long usuarioId);
}
