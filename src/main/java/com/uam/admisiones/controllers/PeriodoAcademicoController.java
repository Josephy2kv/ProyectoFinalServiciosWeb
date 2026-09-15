package com.uam.admisiones.controllers;



import com.uam.admisiones.dto.PeriodoAcademicoDto;
import com.uam.admisiones.services.PeriodoAcademicoService;


import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/periodos-academicos")
public class PeriodoAcademicoController {

    private final PeriodoAcademicoService periodoAcademicoService;

    public PeriodoAcademicoController(
            PeriodoAcademicoService periodoAcademicoService
    ) {
        this.periodoAcademicoService = periodoAcademicoService;
    }

    @GetMapping
    public ResponseEntity<List<PeriodoAcademicoDto>> listarTodos() {

        return ResponseEntity.ok(
                periodoAcademicoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoAcademicoDto> buscarPorId(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                periodoAcademicoService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<PeriodoAcademicoDto> crear(
            @Valid @RequestBody PeriodoAcademicoDto periodo
    ) {

        PeriodoAcademicoDto creado =
                periodoAcademicoService.crear(periodo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoAcademicoDto> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody PeriodoAcademicoDto periodo
    ) {

        return ResponseEntity.ok(
                periodoAcademicoService.actualizar(id, periodo)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(
            @PathVariable Integer id
    ) {

        PeriodoAcademicoDto eliminado =
                periodoAcademicoService.eliminar(id);

        Map<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("estado", HttpStatus.OK.value());
        respuesta.put(
                "mensaje",
                "Período académico eliminado correctamente"
        );
        respuesta.put("periodoAcademico", eliminado);

        return ResponseEntity.ok(respuesta);
    }
}