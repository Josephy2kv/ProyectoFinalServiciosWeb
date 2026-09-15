package com.uam.admisiones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PeriodoAcademicoDto {

    @NotNull(message = "El ID es obligatorio")
    @Positive(message = "El ID debe ser mayor que cero")
    private Integer id;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 20, message = "El código no puede superar los 20 caracteres")
    private String codigo;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String estado;

    @NotNull(message = "La versión activa es obligatoria")
    @Positive(message = "El ID de la versión activa debe ser mayor que cero")
    private Integer versionActivaId;

    public PeriodoAcademicoDto() {
    }

    public PeriodoAcademicoDto(
            Integer id,
            String codigo,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            String estado,
            Integer versionActivaId
    ) {
        this.id = id;
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.versionActivaId = versionActivaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersionActivaId() {
        return versionActivaId;
    }

    public void setVersionActivaId(Integer versionActivaId) {
        this.versionActivaId = versionActivaId;
    }
}