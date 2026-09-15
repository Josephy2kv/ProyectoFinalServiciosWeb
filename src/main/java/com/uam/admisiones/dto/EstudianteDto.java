package com.uam.admisiones.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EstudianteDto {

    @NotBlank(message = "El CIF es obligatorio")
    @Size(max = 15, message = "El CIF no puede superar los 15 caracteres")
    private String cif;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 120, message = "El nombre debe tener entre 3 y 120 caracteres")
    private String nombre;

    @NotNull(message = "El período de admisión es obligatorio")
    @Min(value = 1, message = "El período de admisión debe ser mayor que cero")
    private Integer periodoAdmisionId;

    @NotBlank(message = "El estado de evaluación es obligatorio")
    @Pattern(
            regexp = "PENDIENTE|APROBADO|RECHAZADO",
            message = "El estado de evaluación debe ser PENDIENTE, APROBADO o RECHAZADO"
    )
    private String estadoEvaluacion;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 120, message = "El correo electrónico no puede superar los 120 caracteres")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate fechaNacimiento;

    public EstudianteDto() {
    }

    public EstudianteDto(
            String cif,
            String nombre,
            Integer periodoAdmisionId,
            String estadoEvaluacion,
            String email,
            LocalDate fechaNacimiento
    ) {
        this.cif = cif;
        this.nombre = nombre;
        this.periodoAdmisionId = periodoAdmisionId;
        this.estadoEvaluacion = estadoEvaluacion;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeriodoAdmisionId() {
        return periodoAdmisionId;
    }

    public void setPeriodoAdmisionId(Integer periodoAdmisionId) {
        this.periodoAdmisionId = periodoAdmisionId;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
