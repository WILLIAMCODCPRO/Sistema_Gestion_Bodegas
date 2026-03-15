package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.response.AuditoriaResponseDTO;
import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.model.Auditoria;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaMapper {

    public AuditoriaResponseDTO entidadADTO(Auditoria auditoria, UsuarioResponseDTO usuarioResponseDTO){

        return new AuditoriaResponseDTO(
                auditoria.getId(),
                auditoria.getTipoOperacion(),
                auditoria.getFecha(),
                usuarioResponseDTO,
                auditoria.getEntidad(),
                auditoria.getValorAntiguo(),
                auditoria.getValorNuevo()
        );
    }
}
