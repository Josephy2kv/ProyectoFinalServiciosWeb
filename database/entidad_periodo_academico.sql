CREATE TABLE periodo_academico (
                                   id INT PRIMARY KEY,
                                   codigo VARCHAR(20) NOT NULL,
                                   fecha_inicio DATE NOT NULL,
                                   fecha_fin DATE NOT NULL,
                                   estado VARCHAR(20) NOT NULL,
                                   version_activa_id INT NOT NULL,

                                   CONSTRAINT chk_periodo_fechas
                                       CHECK (fecha_fin >= fecha_inicio)

    -- Relación definida en el modelo:
    -- version_activa_id referencia version_formulario(id)
    --
    -- La FK puede agregarse cuando se cree la tabla version_formulario:
    --
    -- ,CONSTRAINT fk_periodo_version_activa
    --     FOREIGN KEY (version_activa_id)
    --     REFERENCES version_formulario(id)
);