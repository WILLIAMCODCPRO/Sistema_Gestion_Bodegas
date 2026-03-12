package com.s1.gestion_bodegas.repository;

import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {
    List<Inventario> findByStockProductoLessThan(Integer stock);
}
