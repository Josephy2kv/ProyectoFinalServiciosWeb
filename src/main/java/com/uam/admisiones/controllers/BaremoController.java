package com.uam.admisiones.controllers;

import com.uam.admisiones.dto.BaremoDto;
import com.uam.admisiones.services.BaremoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/baremos")
public class BaremoController {

    private final BaremoService baremoService;

    public BaremoController(BaremoService baremoService) {
        this.baremoService = baremoService;
    }

    @GetMapping
    public ResponseEntity<List<BaremoDto>> listarTodos() {

        return ResponseEntity.ok(
                baremoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaremoDto> buscarPorId(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                baremoService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<BaremoDto> crear(
            @Valid @RequestBody BaremoDto baremo
    ) {

        BaremoDto creado = baremoService.crear(baremo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaremoDto> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody BaremoDto baremo
    ) {

        return ResponseEntity.ok(
                baremoService.actualizar(id, baremo)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(
            @PathVariable Integer id
    ) {

        BaremoDto eliminado = baremoService.eliminar(id);

        Map<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("estado", HttpStatus.OK.value());
        respuesta.put("mensaje", "Baremo eliminado correctamente");
        respuesta.put("baremo", eliminado);

        return ResponseEntity.ok(respuesta);
    }
}
