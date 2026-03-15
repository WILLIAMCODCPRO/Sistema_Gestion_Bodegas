package com.s1.gestion_bodegas.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s1.gestion_bodegas.auth.AuthService;
import com.s1.gestion_bodegas.model.Auditoria;
import com.s1.gestion_bodegas.model.TipoOperacion;
import com.s1.gestion_bodegas.repository.AuditoriaRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditoriaListener {

    private final AuditoriaRepository auditoriaRepository;
    private final AuthService authService;




    @PrePersist
    public void prePersist(Object entidad){
        ObjectMapper mapper = new ObjectMapper();
        Auditoria auditoria = new Auditoria();
        auditoria.setTipoOperacion(TipoOperacion.INSERT);
        auditoria.setUsuario(authService.obtenerUsuarioAutenticado());
        auditoria.setEntidad(entidad.getClass().getSimpleName());
        try {
            auditoria.setValorAntiguo(mapper.writeValueAsString(entidad));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        auditoria.setValorNuevo(null);

        auditoriaRepository.save(auditoria);
    }


//   @PostUpdate
//    public void preUpdate(Object entidad){
//        ObjectMapper mapper = new ObjectMapper();
//        Auditoria auditoria = new Auditoria();
//        auditoria.setTipoOperacion(TipoOperacion.UPDATE);
//        auditoria.setUsuario(authService.obtenerUsuarioAutenticado());
//        auditoria.setEntidad(entidad.getClass().getSimpleName());
//        try {
//            auditoria.setValorNuevo(mapper.writeValueAsString(entidad));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        auditoria.setValorAntiguo(null);
//        auditoriaRepository.save(auditoria);
//    }

    @PreRemove
    public void preRemove(Object entidad){
        ObjectMapper mapper = new ObjectMapper();
        Auditoria auditoria = new Auditoria();
        auditoria.setTipoOperacion(TipoOperacion.DELETE);
        auditoria.setUsuario(authService.obtenerUsuarioAutenticado());
        auditoria.setEntidad(entidad.getClass().getSimpleName());
        try {
            auditoria.setValorAntiguo(mapper.writeValueAsString(entidad));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        auditoria.setValorNuevo(null);
        auditoriaRepository.save(auditoria);
    }
}
