-- Versión corregida de la entidad estudiante para cumplir el taller.
-- El enunciado exige: identificador + al menos 5 atributos adicionales.
-- Tu diagrama original tenía únicamente 3 atributos adicionales.
-- Se agregan email y fecha_nacimiento.

CREATE TABLE estudiante (
    cif VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    periodo_admision_id INT NOT NULL,
    estado_evaluacion VARCHAR(20) NOT NULL,
    email VARCHAR(120) NOT NULL,
    fecha_nacimiento DATE NOT NULL,

    CONSTRAINT chk_estado_evaluacion
        CHECK (estado_evaluacion IN ('PENDIENTE', 'APROBADO', 'RECHAZADO'))

    -- Cuando exista la tabla periodo_admision, puede agregarse:
    -- ,CONSTRAINT fk_estudiante_periodo
    --     FOREIGN KEY (periodo_admision_id)
    --     REFERENCES periodo_admision(id)
);
