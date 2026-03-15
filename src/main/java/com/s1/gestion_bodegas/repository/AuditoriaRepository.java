package com.s1.gestion_bodegas.repository;

import com.s1.gestion_bodegas.model.Auditoria;
import com.s1.gestion_bodegas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria,Long> {
    List<Auditoria> findByUsuario(Usuario usuario);;
}
