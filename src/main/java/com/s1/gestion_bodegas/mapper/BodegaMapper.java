package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.request.BodegaRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.model.Bodega;
import org.springframework.stereotype.Component;

@Component
public class BodegaMapper {

    public BodegaResponseDTO entidadADTO(Bodega bodega) {
        if (bodega == null) return null;
        return new BodegaResponseDTO(
                bodega.getId(),
                bodega.getNombre(),
                bodega.getUbicacion(),
                bodega.getCapacidad(),
                bodega.getEncargado()
        );
    }

    public Bodega DTOAEntidad(BodegaRequestDTO bodegaRequestDTO) {
        if (bodegaRequestDTO == null) return null;
        Bodega bodega = new Bodega();
        bodega.setNombre(bodegaRequestDTO.nombre());
        bodega.setUbicacion(bodegaRequestDTO.ubicacion());
        bodega.setCapacidad(bodegaRequestDTO.capacidad());
        bodega.setEncargado(bodegaRequestDTO.encargado());
        return bodega;

    }

    public void actualizarEntidadDesdeDTO(BodegaRequestDTO bodegaRequestDTO, Bodega bodega){
        if (bodegaRequestDTO == null || bodega == null) return;
        bodega.setNombre(bodegaRequestDTO.nombre());
        bodega.setUbicacion(bodegaRequestDTO.ubicacion());
        bodega.setCapacidad(bodegaRequestDTO.capacidad());
        bodega.setEncargado(bodegaRequestDTO.encargado());
    }

}
