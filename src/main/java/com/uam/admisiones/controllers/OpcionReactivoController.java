package com.uam.admisiones.controllers;

import com.uam.admisiones.dto.OpcionReactivoDto;
import com.uam.admisiones.services.OpcionReactivoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opciones-reactivos")
public class OpcionReactivoController {

    private final OpcionReactivoService opcionReactivoService;

    public OpcionReactivoController(OpcionReactivoService opcionReactivoService) {
        this.opcionReactivoService = opcionReactivoService;
    }

    @GetMapping
    public ResponseEntity<List<OpcionReactivoDto>> listarTodos() {
        return ResponseEntity.ok(
                opcionReactivoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpcionReactivoDto> obtenerPorId(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                opcionReactivoService.obtenerPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<OpcionReactivoDto> crear(
            @Valid @RequestBody OpcionReactivoDto dto
    ) {
        OpcionReactivoDto creado = opcionReactivoService.crear(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpcionReactivoDto> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OpcionReactivoDto dto
    ) {
        return ResponseEntity.ok(
                opcionReactivoService.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id
    ) {
        opcionReactivoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}