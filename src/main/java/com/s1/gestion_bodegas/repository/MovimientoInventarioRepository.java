package com.s1.gestion_bodegas.repository;

import com.s1.gestion_bodegas.model.MovimientoInventario;
import com.s1.gestion_bodegas.model.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario,Long> {
    List<MovimientoInventario> findByFechaBetween(LocalDateTime fecha1, LocalDateTime fecha2);

    List<MovimientoInventario> findTop10ByOrderByFechaDesc();

    Long countByTipoMovimiento(TipoMovimiento tipoMovimiento);

}
