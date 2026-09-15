package com.uam.admisiones.exceptions;

import java.util.Map;

public class ValidationErrorResponse {

    private int estado;
    private String mensaje;
    private Map<String, String> errores;

    public ValidationErrorResponse(
            int estado,
            String mensaje,
            Map<String, String> errores
    ) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.errores = errores;
    }

    public int getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Map<String, String> getErrores() {
        return errores;
    }
}
