package com.uam.admisiones.services;

import com.uam.admisiones.dto.OpcionReactivoDto;
import com.uam.admisiones.exceptions.RegistroDuplicadoException;
import com.uam.admisiones.exceptions.RegistroNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpcionReactivoService {

    private final Map<Integer, OpcionReactivoDto> opciones = new LinkedHashMap<>();

    public List<OpcionReactivoDto> listarTodos() {
        return new ArrayList<>(opciones.values());
    }

    public OpcionReactivoDto obtenerPorId(Integer id) {

        OpcionReactivoDto opcion = opciones.get(id);

        if (opcion == null) {
            throw new RegistroNoEncontradoException(
                    "No se encontró la opción de reactivo con id: " + id
            );
        }

        return opcion;
    }

    public OpcionReactivoDto crear(OpcionReactivoDto dto) {

        if (opciones.containsKey(dto.getId())) {
            throw new RegistroDuplicadoException(
                    "Ya existe una opción de reactivo con id: " + dto.getId()
            );
        }

        opciones.put(dto.getId(), dto);

        return dto;
    }

    public OpcionReactivoDto actualizar(Integer id, OpcionReactivoDto dto) {

        if (!opciones.containsKey(id)) {
            throw new RegistroNoEncontradoException(
                    "No se encontró la opción de reactivo con id: " + id
            );
        }

        dto.setId(id);
        opciones.put(id, dto);

        return dto;
    }

    public void eliminar(Integer id) {

        if (!opciones.containsKey(id)) {
            throw new RegistroNoEncontradoException(
                    "No se encontró la opción de reactivo con id: " + id
            );
        }

        opciones.remove(id);
    }
}
