package com.uam.admisiones.exceptions;

public class ApiErrorResponse {

    private int estado;
    private String mensaje;

    public ApiErrorResponse(int estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }
}
