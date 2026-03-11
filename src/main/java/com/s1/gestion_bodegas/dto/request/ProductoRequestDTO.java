package com.s1.gestion_bodegas.dto.request;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record ProductoRequestDTO(

        String nombre,

        String categoria,

        BigDecimal precio
) {
}
