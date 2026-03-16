package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.response.UsuarioResponseDTO;
import com.s1.gestion_bodegas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
@Validated
public class UsuarioController {
    public final UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(){
        return ResponseEntity.ok().body(usuarioServiceImpl.listarUsuarios());
    }
}
