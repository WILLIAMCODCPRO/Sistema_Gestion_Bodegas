package com.s1.gestion_bodegas.repository;

import com.s1.gestion_bodegas.dto.response.ProductoMasMovidoResponseDTO;
import com.s1.gestion_bodegas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByStockProductoLessThan(Integer stock);
    @Query("""
    SELECT new com.s1.gestion_bodegas.dto.response.ProductoMasMovidoResponseDTO(
        p.id, p.nombre, SUM(mi.cantidadProducto)
    )
    FROM Producto p
    JOIN MovimientoInventario mi ON mi.producto = p
    GROUP BY p.id, p.nombre
    ORDER BY SUM(mi.cantidadProducto) DESC
""")
    List<ProductoMasMovidoResponseDTO> findProductosMasMovidos();
}
