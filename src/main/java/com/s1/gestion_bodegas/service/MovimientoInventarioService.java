package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;

import java.util.List;

public interface MovimientoInventarioService {
    List<MovimientoInventarioResponseDTO> listarMovimientos();
}
