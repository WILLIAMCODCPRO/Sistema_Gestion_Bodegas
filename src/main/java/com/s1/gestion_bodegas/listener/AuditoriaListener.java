package com.s1.gestion_bodegas.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s1.gestion_bodegas.auth.AuthService;
import com.s1.gestion_bodegas.model.Auditoria;
import com.s1.gestion_bodegas.model.MovimientoInventario;
import com.s1.gestion_bodegas.model.Producto;
import com.s1.gestion_bodegas.model.TipoOperacion;
import com.s1.gestion_bodegas.repository.AuditoriaRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

            if(entidad instanceof MovimientoInventario movimientoInventario){

                Map<String,Object> datos = new HashMap<>();

                datos.put("tipoMovimiento", movimientoInventario.getTipoMovimiento());
                datos.put("cantidadProducto", movimientoInventario.getCantidadProducto());
                datos.put("productoId", movimientoInventario.getProducto().getId());

                datos.put("bodegaOrigenId",
                        movimientoInventario.getBodegaOrigen() != null ? movimientoInventario.getBodegaOrigen().getId() : null);

                datos.put("bodegaDestinoId",
                        movimientoInventario.getBodegaDestino() != null ? movimientoInventario.getBodegaDestino().getId() : null);

                datos.put("usuarioId", movimientoInventario.getUsuario().getId());

                auditoria.setValorNuevo(mapper.writeValueAsString(datos));

            }
            else if(entidad instanceof Producto producto){

                Map<String,Object> datos = new HashMap<>();

                datos.put("nombre", producto.getNombre());
                datos.put("categoria", producto.getCategoria());
                datos.put("precio", producto.getPrecio());
                datos.put("stockProducto", producto.getStockProducto());
                datos.put("bodegaId", producto.getBodega().getId());

                auditoria.setValorNuevo(mapper.writeValueAsString(datos));

            }
            else{

                auditoria.setValorNuevo(mapper.writeValueAsString(entidad));

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

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

            if(entidad instanceof MovimientoInventario movimientoInventario){

                Map<String,Object> datos = new HashMap<>();

                datos.put("tipoMovimiento", movimientoInventario.getTipoMovimiento());
                datos.put("cantidadProducto", movimientoInventario.getCantidadProducto());
                datos.put("productoId", movimientoInventario.getProducto().getId());

                datos.put("bodegaOrigenId",
                        movimientoInventario.getBodegaOrigen() != null ? movimientoInventario.getBodegaOrigen().getId() : null);

                datos.put("bodegaDestinoId",
                        movimientoInventario.getBodegaDestino() != null ? movimientoInventario.getBodegaDestino().getId() : null);

                datos.put("usuarioId", movimientoInventario.getUsuario().getId());

                auditoria.setValorAntiguo(mapper.writeValueAsString(datos));

            }
            else if(entidad instanceof Producto producto){

                Map<String,Object> datos = new HashMap<>();

                datos.put("nombre", producto.getNombre());
                datos.put("categoria", producto.getCategoria());
                datos.put("precio", producto.getPrecio());
                datos.put("stockProducto", producto.getStockProducto());
                datos.put("bodegaId", producto.getBodega().getId());

                auditoria.setValorAntiguo(mapper.writeValueAsString(datos));

            }
            else{

                auditoria.setValorAntiguo(mapper.writeValueAsString(entidad));

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        auditoriaRepository.save(auditoria);
    }
}
