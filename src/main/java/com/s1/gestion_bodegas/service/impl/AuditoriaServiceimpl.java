package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;
import com.s1.gestion_bodegas.mapper.AuditoriaMapper;
import com.s1.gestion_bodegas.mapper.UsuarioMapper;
import com.s1.gestion_bodegas.model.Auditoria;
import com.s1.gestion_bodegas.model.Usuario;
import com.s1.gestion_bodegas.repository.AuditoriaRepository;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import com.s1.gestion_bodegas.service.AuditoriaService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public AuditoriaResponseDTO listarAuditoriaID(Long id) {
        Auditoria auditoria = auditoriaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No existe dicha auditoria"));
        Usuario usuario = usuarioRepository.findById(auditoria.getUsuario().getId()).orElseThrow();
        return auditoriaMapper.entidadADTO(auditoria, usuarioMapper.entidadADTO(usuario));
    }

    @Override
    public List<AuditoriaResponseDTO> auditoriaUsuario(Long usuarioID) {
        Usuario usuario = usuarioRepository.findById(usuarioID).orElseThrow(()->new EntityNotFoundException("No existe dicho usuario no s epuede filtar"));
        return auditoriaRepository.findByUsuario(usuario)
                .stream()
                .map(dato -> auditoriaMapper.entidadADTO(dato,usuarioMapper.entidadADTO(usuarioRepository.findById(dato.getUsuario().getId()).orElseThrow()))).toList();
    }
}
