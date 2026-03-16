package com.s1.gestion_bodegas.controller;

import com.s1.gestion_bodegas.dto.request.BodegaRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.service.impl.BodegaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodega")
@RequiredArgsConstructor
@Validated
public class BodegaController {
    private final BodegaServiceImpl bodegaService;

    @GetMapping
    public ResponseEntity<List<BodegaResponseDTO>> listarBodegas(){
        return ResponseEntity.ok().body(bodegaService.listarBodegas());
    }

    @PostMapping
    public ResponseEntity<BodegaResponseDTO>registrarBodega(@RequestBody @Validated BodegaRequestDTO bodegaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.registrarBodega(bodegaRequestDTO));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BodegaResponseDTO>buscarBodegaID(@PathVariable  Long id){
        return ResponseEntity.ok().body(bodegaService.buscarBodegaID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO>actualizarBodega(@PathVariable Long id, @RequestBody @Validated BodegaRequestDTO bodegaRequestDTO){
        return ResponseEntity.ok().body(bodegaService.actualizarBodega(bodegaRequestDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminarBodega(@PathVariable Long id){
        bodegaService.eliminarBodega(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
