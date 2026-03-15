package com.s1.gestion_bodegas.exception;

import java.time.LocalDateTime;

public record ErrorResponse
        (LocalDateTime timestamp, int status, String message, String errorCode){
}
