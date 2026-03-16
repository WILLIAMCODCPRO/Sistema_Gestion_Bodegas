package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> listarUsuarios();
}
