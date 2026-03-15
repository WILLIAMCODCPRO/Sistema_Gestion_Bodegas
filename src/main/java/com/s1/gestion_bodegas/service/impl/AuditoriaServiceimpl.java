package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;
import com.s1.gestion_bodegas.mapper.AuditoriaMapper;
import com.s1.gestion_bodegas.mapper.UsuarioMapper;
import com.s1.gestion_bodegas.repository.AuditoriaRepository;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import com.s1.gestion_bodegas.service.AuditoriaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuditoriaServiceimpl implements AuditoriaService {

    public final AuditoriaRepository auditoriaRepository;
    public final AuditoriaMapper auditoriaMapper;
    public final UsuarioRepository usuarioRepository;
    public final UsuarioMapper usuarioMapper;

    @Override
    public List<AuditoriaResponseDTO> listarAuditorias() {
        return auditoriaRepository.findAll()
                .stream()
                .map(dato -> auditoriaMapper.entidadADTO(dato,usuarioMapper.entidadADTO(usuarioRepository.findById(dato.getUsuario().getId()).orElseThrow()))).toList();
    }
}
