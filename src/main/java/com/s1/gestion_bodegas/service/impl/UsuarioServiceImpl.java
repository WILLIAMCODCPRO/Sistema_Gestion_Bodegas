package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.mapper.UsuarioMapper;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import com.s1.gestion_bodegas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    public final UsuarioMapper usuarioMapper;
    public final UsuarioRepository usuarioRepository;


    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::entidadADTO).toList();
    }
}
