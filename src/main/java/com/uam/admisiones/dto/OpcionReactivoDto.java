package com.uam.admisiones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OpcionReactivoDto {

    @NotNull(message = "El id es obligatorio")
    @Positive(message = "El id debe ser mayor que cero")
    private Integer id;

    @NotNull(message = "El reactivoId es obligatorio")
    @Positive(message = "El reactivoId debe ser mayor que cero")
    private Integer reactivoId;

    @NotBlank(message = "El literal es obligatorio")
    @Size(min = 1, max = 1, message = "El literal debe contener exactamente un carácter")
    private String literal;

    @Size(max = 255, message = "El texto no puede superar los 255 caracteres")
    private String texto;

    @Size(max = 255, message = "La imagen no puede superar los 255 caracteres")
    private String imagen;

    @NotNull(message = "El orden es obligatorio")
    @Positive(message = "El orden debe ser mayor que cero")
    private Integer orden;

    @NotNull(message = "esCorrecta es obligatorio")
    private Boolean esCorrecta;

    public OpcionReactivoDto() {
    }

    public OpcionReactivoDto(
            Integer id,
            Integer reactivoId,
            String literal,
            String texto,
            String imagen,
            Integer orden,
            Boolean esCorrecta
    ) {
        this.id = id;
        this.reactivoId = reactivoId;
        this.literal = literal;
        this.texto = texto;
        this.imagen = imagen;
        this.orden = orden;
        this.esCorrecta = esCorrecta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReactivoId() {
        return reactivoId;
    }

    public void setReactivoId(Integer reactivoId) {
        this.reactivoId = reactivoId;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(Boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }
}
