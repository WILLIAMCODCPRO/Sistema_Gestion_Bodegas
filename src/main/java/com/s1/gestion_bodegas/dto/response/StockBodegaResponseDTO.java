package com.s1.gestion_bodegas.dto.response;

public record StockBodegaResponseDTO(
        Long idBodega,
        String nombreBodega,
        Long stockTotal
) {
}
