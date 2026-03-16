package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.request.UsuarioRequestDTO;
import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponseDTO entidadADTO(Usuario usuario){
        if (usuario == null) return null;
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getRol()
        );
    }

    public Usuario DTOAEntidad (UsuarioRequestDTO usuarioRequestDTO){
        if (usuarioRequestDTO == null) return null;
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioRequestDTO.nombreUsuario());
        usuario.setPassword(usuarioRequestDTO.password());
        usuario.setRol(usuarioRequestDTO.rol());
        return usuario;
    }

}
