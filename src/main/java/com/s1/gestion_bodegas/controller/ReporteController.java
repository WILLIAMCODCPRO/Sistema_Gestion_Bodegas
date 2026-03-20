package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.ProductoMasMovidoResponseDTO;
import com.s1.gestion_bodegas.dto.response.StockBodegaResponseDTO;
import com.s1.gestion_bodegas.model.TipoMovimiento;
import com.s1.gestion_bodegas.service.impl.MovimientoInventarioServiceimpl;
import com.s1.gestion_bodegas.service.impl.ReporteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
@Validated
@Tag(name = "Reportes", description = "Operaciones relacionadas con reportes de inventario y productos")
public class ReporteController {

    private final ReporteServiceImpl reporteServiceImpl;
    private final MovimientoInventarioServiceimpl movimientoInventarioServiceimpl;

    @Operation(summary = "Stock por bodega", description = "Obtiene el stock disponible de productos agrupado por cada bodega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock por bodega obtenido correctamente",
                    content = @Content(schema = @Schema(implementation = StockBodegaResponseDTO.class)))
    })
    @GetMapping("/bodega/stock")
    public ResponseEntity<List<StockBodegaResponseDTO>> obtenerStockPorBodega() {
        return ResponseEntity.ok(reporteServiceImpl.stockBodega());
    }

    @Operation(summary = "Productos más movidos", description = "Obtiene la lista de productos con mayor movimiento en el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos más movidos obtenidos correctamente",
                    content = @Content(schema = @Schema(implementation = ProductoMasMovidoResponseDTO.class)))
    })
    @GetMapping("/productos/masmovidos")
    public ResponseEntity<List<ProductoMasMovidoResponseDTO>> obtenerProductosMasMovidos() {
        return ResponseEntity.ok(reporteServiceImpl.productosMasMovidos());
    }

    @GetMapping("/movimientos/{tipoMovimiento}")
    public ResponseEntity<Long> listarRecientes(@PathVariable TipoMovimiento tipoMovimiento){
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.listarTipoMovimiento(tipoMovimiento));
    }

    @GetMapping("/movimientos/totales")
    public ResponseEntity<Long> listarRecientes(){
        return ResponseEntity.ok().body(movimientoInventarioServiceimpl.movimeintosTotales());
    }
}
