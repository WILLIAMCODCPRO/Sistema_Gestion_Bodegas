package com.s1.gestion_bodegas.repository;

import com.s1.gestion_bodegas.dto.response.StockBodegaResponseDTO;
import com.s1.gestion_bodegas.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega,Long> {
    @Query("SELECT new com.s1.gestion_bodegas.dto.response.StockBodegaResponseDTO(" +
            "b.id, b.nombre, SUM(p.stockProducto)) " +
            "FROM Bodega b JOIN Producto p ON p.bodega = b " +
            "GROUP BY b.id, b.nombre " +
            "ORDER BY SUM(p.stockProducto) DESC")
    List<StockBodegaResponseDTO> obtenerStockPorBodega();
}
