package com.s1.gestion_bodegas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_inventario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private TipoMovimiento tipo_movimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bodega_origen")
    private Bodega bodega_origen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = " id_bodega_destino")
    private Bodega bodega_destino;
}
