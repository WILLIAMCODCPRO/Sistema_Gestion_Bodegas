package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.MovimientoInventarioResponseDTO;
import com.s1.gestion_bodegas.mapper.MovimientoInventarioMapper;
import com.s1.gestion_bodegas.repository.MovimientoInventarioRepository;
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
    @Override
    public List<MovimientoInventarioResponseDTO> listarMovimientos() {
        return movimientoInventarioRepository.findAll().stream().map(dato ->)
    }
}
