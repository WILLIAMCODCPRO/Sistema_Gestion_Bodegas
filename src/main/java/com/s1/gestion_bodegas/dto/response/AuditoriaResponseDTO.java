package com.s1.gestion_bodegas.dto.response;

import com.s1.gestion_bodegas.model.TipoOperacion;
import com.s1.gestion_bodegas.model.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public record AuditoriaResponseDTO(
        Long id,

        TipoOperacion tipoOperacion,

        LocalDateTime fecha,

        UsuarioResponseDTO usuario,

        String entidad,

        String valorAntiguo,

        String valorNuevo
) {
}
