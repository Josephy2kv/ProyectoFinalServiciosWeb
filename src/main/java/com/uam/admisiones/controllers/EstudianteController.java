package com.uam.admisiones.controllers;

import com.uam.admisiones.dto.EstudianteDto;
import com.uam.admisiones.services.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDto>> listarTodos() {
        return ResponseEntity.ok(estudianteService.listarTodos());
    }

    @GetMapping("/{cif}")
    public ResponseEntity<EstudianteDto> buscarPorCif(@PathVariable String cif) {
        return ResponseEntity.ok(estudianteService.buscarPorCif(cif));
    }

    @PostMapping
    public ResponseEntity<EstudianteDto> crear(
            @Valid @RequestBody EstudianteDto estudiante
    ) {
        EstudianteDto creado = estudianteService.crear(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{cif}")
    public ResponseEntity<EstudianteDto> actualizar(
            @PathVariable String cif,
            @Valid @RequestBody EstudianteDto estudiante
    ) {
        return ResponseEntity.ok(estudianteService.actualizar(cif, estudiante));
    }

    @DeleteMapping("/{cif}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable String cif) {
        EstudianteDto eliminado = estudianteService.eliminar(cif);

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("estado", HttpStatus.OK.value());
        respuesta.put("mensaje", "Estudiante eliminado correctamente");
        respuesta.put("estudiante", eliminado);

        return ResponseEntity.ok(respuesta);
    }
}
