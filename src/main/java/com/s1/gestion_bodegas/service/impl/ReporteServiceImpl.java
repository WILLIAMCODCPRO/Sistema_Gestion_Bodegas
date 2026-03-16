package com.s1.gestion_bodegas.service.impl;

import com.s1.gestion_bodegas.dto.response.ProductoMasMovidoResponseDTO;
import com.s1.gestion_bodegas.dto.response.StockBodegaResponseDTO;
import com.s1.gestion_bodegas.repository.BodegaRepository;
import com.s1.gestion_bodegas.repository.ProductoRepository;
import com.s1.gestion_bodegas.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {
    public final BodegaRepository bodegaRepository;
    public final ProductoRepository productoRepository;

    @Override
    public List<StockBodegaResponseDTO> stockBodega() {
        return bodegaRepository.obtenerStockPorBodega();
    }

    @Override
    public List<ProductoMasMovidoResponseDTO> productosMasMovidos() {
        return productoRepository.findProductosMasMovidos();
    }


}
