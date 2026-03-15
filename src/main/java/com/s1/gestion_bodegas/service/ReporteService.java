package com.s1.gestion_bodegas.service;

import com.s1.gestion_bodegas.dto.response.ProductoMasMovidoResponseDTO;
import com.s1.gestion_bodegas.dto.response.StockBodegaResponseDTO;

import java.util.List;

public interface ReporteService {
    List<StockBodegaResponseDTO> stockBodega();

    List<ProductoMasMovidoResponseDTO> productosMasMovidos();
}
