package com.uam.admisiones.services;

import com.uam.admisiones.dto.EstudianteDto;
import com.uam.admisiones.exceptions.RegistroDuplicadoException;
import com.uam.admisiones.exceptions.RegistroNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    private final Map<String, EstudianteDto> estudiantes = new LinkedHashMap<>();

    public List<EstudianteDto> listarTodos() {
        return new ArrayList<>(estudiantes.values());
    }

    public EstudianteDto buscarPorCif(String cif) {
        EstudianteDto estudiante = estudiantes.get(cif);

        if (estudiante == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un estudiante con CIF " + cif
            );
        }

        return estudiante;
    }

    public EstudianteDto crear(EstudianteDto estudiante) {
        if (estudiantes.containsKey(estudiante.getCif())) {
            throw new RegistroDuplicadoException(
                    "Ya existe un estudiante con CIF " + estudiante.getCif()
            );
        }

        EstudianteDto nuevo = copiar(estudiante);
        estudiantes.put(nuevo.getCif(), nuevo);
        return nuevo;
    }

    public EstudianteDto actualizar(String cif, EstudianteDto datosActualizados) {
        if (!estudiantes.containsKey(cif)) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un estudiante con CIF " + cif
            );
        }

        if (!cif.equals(datosActualizados.getCif())
                && estudiantes.containsKey(datosActualizados.getCif())) {
            throw new RegistroDuplicadoException(
                    "Ya existe un estudiante con CIF " + datosActualizados.getCif()
            );
        }

        estudiantes.remove(cif);
        EstudianteDto actualizado = copiar(datosActualizados);
        estudiantes.put(actualizado.getCif(), actualizado);

        return actualizado;
    }

    public EstudianteDto eliminar(String cif) {
        EstudianteDto eliminado = estudiantes.remove(cif);

        if (eliminado == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un estudiante con CIF " + cif
            );
        }

        return eliminado;
    }

    private EstudianteDto copiar(EstudianteDto estudiante) {
        return new EstudianteDto(
                estudiante.getCif(),
                estudiante.getNombre(),
                estudiante.getPeriodoAdmisionId(),
                estudiante.getEstadoEvaluacion(),
                estudiante.getEmail(),
                estudiante.getFechaNacimiento()
        );
    }
}
