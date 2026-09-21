package com.uam.admisiones.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class BaremoDto {

    @NotNull(message = "El ID es obligatorio")
    @Positive(message = "El ID debe ser mayor que cero")
    private Integer id;

    @NotNull(message = "La versión del formulario es obligatoria")
    @Positive(message = "El ID de la versión del formulario debe ser mayor que cero")
    private Integer versionFormularioId;

    @NotBlank(message = "El factor espacial es obligatorio")
    @Pattern(
            regexp = "S1A|S1B|S1|S2|ST",
            message = "El factor debe ser S1A, S1B, S1, S2 o ST"
    )
    private String factor;

    @NotNull(message = "La puntuación directa es obligatoria")
    @Min(value = 0, message = "La puntuación directa no puede ser negativa")
    private Integer puntuacionDirecta;

    @NotNull(message = "El percentil es obligatorio")
    @Min(value = 1, message = "El percentil debe ser como mínimo 1")
    @Max(value = 99, message = "El percentil no puede superar 99")
    private Integer percentil;

    @NotNull(message = "La fecha de vigencia es obligatoria")
    private LocalDate vigencia;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
            regexp = "ACTIVO|INACTIVO",
            message = "El estado debe ser ACTIVO o INACTIVO"
    )
    private String estado;

    public BaremoDto() {
    }

    public BaremoDto(
            Integer id,
            Integer versionFormularioId,
            String factor,
            Integer puntuacionDirecta,
            Integer percentil,
            LocalDate vigencia,
            String estado
    ) {
        this.id = id;
        this.versionFormularioId = versionFormularioId;
        this.factor = factor;
        this.puntuacionDirecta = puntuacionDirecta;
        this.percentil = percentil;
        this.vigencia = vigencia;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionFormularioId() {
        return versionFormularioId;
    }

    public void setVersionFormularioId(Integer versionFormularioId) {
        this.versionFormularioId = versionFormularioId;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public Integer getPuntuacionDirecta() {
        return puntuacionDirecta;
    }

    public void setPuntuacionDirecta(Integer puntuacionDirecta) {
        this.puntuacionDirecta = puntuacionDirecta;
    }

    public Integer getPercentil() {
        return percentil;
    }

    public void setPercentil(Integer percentil) {
        this.percentil = percentil;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
