package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.ProductoMasMovidoResponseDTO;
import com.s1.gestion_bodegas.dto.response.StockBodegaResponseDTO;
import com.s1.gestion_bodegas.service.impl.ReporteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
@Validated
public class ReporteController {
    public final ReporteServiceImpl reporteServiceImpl;

    @GetMapping("/bodega/stock")
    public ResponseEntity<List<StockBodegaResponseDTO>> obtenerStockPorBodega() {
        return ResponseEntity.ok(reporteServiceImpl.stockBodega());
    }

    @GetMapping("/productos/masmovidos")
    public ResponseEntity<List<ProductoMasMovidoResponseDTO>> obtenerProductosMasMovidos() {
        return ResponseEntity.ok(reporteServiceImpl.productosMasMovidos());
    }
}
