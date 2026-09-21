package com.uam.admisiones.services;

import com.uam.admisiones.dto.BaremoDto;
import com.uam.admisiones.exceptions.RegistroDuplicadoException;
import com.uam.admisiones.exceptions.RegistroNoEncontradoException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaremoService {

    private final Map<Integer, BaremoDto> baremos = new LinkedHashMap<>();

    public List<BaremoDto> listarTodos() {
        return new ArrayList<>(baremos.values());
    }

    public BaremoDto buscarPorId(Integer id) {

        BaremoDto baremo = baremos.get(id);

        if (baremo == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un baremo con ID " + id
            );
        }

        return baremo;
    }

    public BaremoDto crear(BaremoDto baremo) {

        if (baremos.containsKey(baremo.getId())) {
            throw new RegistroDuplicadoException(
                    "Ya existe un baremo con ID " + baremo.getId()
            );
        }

        BaremoDto nuevo = copiar(baremo);

        baremos.put(nuevo.getId(), nuevo);

        return nuevo;
    }

    public BaremoDto actualizar(Integer id, BaremoDto baremoActualizado) {

        if (!baremos.containsKey(id)) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un baremo con ID " + id
            );
        }

        /*
         * El ID de la URL identifica el registro.
         * No permitimos cambiar la PK desde el body.
         */
        baremoActualizado.setId(id);

        BaremoDto actualizado = copiar(baremoActualizado);

        baremos.put(id, actualizado);

        return actualizado;
    }

    public BaremoDto eliminar(Integer id) {

        BaremoDto eliminado = baremos.remove(id);

        if (eliminado == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró un baremo con ID " + id
            );
        }

        return eliminado;
    }

    private BaremoDto copiar(BaremoDto baremo) {

        return new BaremoDto(
                baremo.getId(),
                baremo.getVersionFormularioId(),
                baremo.getFactor(),
                baremo.getPuntuacionDirecta(),
                baremo.getPercentil(),
                baremo.getVigencia(),
                baremo.getEstado()
        );
    }
}
