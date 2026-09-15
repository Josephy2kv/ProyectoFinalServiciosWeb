package com.uam.admisiones.services;



import com.uam.admisiones.dto.PeriodoAcademicoDto;
import com.uam.admisiones.exceptions.RegistroNoEncontradoException;
import com.uam.admisiones.exceptions.RegistroDuplicadoException;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeriodoAcademicoService {

    private final Map<Integer, PeriodoAcademicoDto> periodos = new LinkedHashMap<>();

    public List<PeriodoAcademicoDto> listarTodos() {
        return new ArrayList<>(periodos.values());
    }

    public PeriodoAcademicoDto buscarPorId(Integer id) {

        PeriodoAcademicoDto periodo = periodos.get(id);

        if (periodo == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un período académico con ID " + id
            );
        }

        return periodo;
    }

    public PeriodoAcademicoDto crear(PeriodoAcademicoDto periodo) {

        if (periodos.containsKey(periodo.getId())) {
            throw new RegistroDuplicadoException(
                    "Ya existe un período académico con ID " + periodo.getId()
            );
        }

        validarFechas(periodo);

        PeriodoAcademicoDto nuevo = copiar(periodo);

        periodos.put(nuevo.getId(), nuevo);

        return nuevo;
    }

    public PeriodoAcademicoDto actualizar(
            Integer id,
            PeriodoAcademicoDto periodoActualizado
    ) {

        if (!periodos.containsKey(id)) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un período académico con ID " + id
            );
        }

        validarFechas(periodoActualizado);

        /*
         * El ID de la URL identifica el registro.
         * No permitimos cambiar la PK desde el body.
         */
        periodoActualizado.setId(id);

        PeriodoAcademicoDto actualizado = copiar(periodoActualizado);

        periodos.put(id, actualizado);

        return actualizado;
    }

    public PeriodoAcademicoDto eliminar(Integer id) {

        PeriodoAcademicoDto eliminado = periodos.remove(id);

        if (eliminado == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un período académico con ID " + id
            );
        }

        return eliminado;
    }

    private void validarFechas(PeriodoAcademicoDto periodo) {

        if (periodo.getFechaInicio() != null
                && periodo.getFechaFin() != null
                && periodo.getFechaFin().isBefore(periodo.getFechaInicio())) {

            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser anterior a la fecha de inicio"
            );
        }
    }

    private PeriodoAcademicoDto copiar(PeriodoAcademicoDto periodo) {

        return new PeriodoAcademicoDto(
                periodo.getId(),
                periodo.getCodigo(),
                periodo.getFechaInicio(),
                periodo.getFechaFin(),
                periodo.getEstado(),
                periodo.getVersionActivaId()
        );
    }
}