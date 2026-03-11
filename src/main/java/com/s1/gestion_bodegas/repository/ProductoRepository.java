package com.s1.gestion_bodegas.repository;

import com.s1.gestion_bodegas.model.Inventario;
import com.s1.gestion_bodegas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
