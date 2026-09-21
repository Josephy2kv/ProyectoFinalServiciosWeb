CREATE TABLE baremo (
                        id INT PRIMARY KEY,
                        version_formulario_id INT NOT NULL,
                        factor VARCHAR(10) NOT NULL,
                        puntuacion_directa INT NOT NULL,
                        percentil INT NOT NULL,
                        vigencia DATE NOT NULL,
                        estado VARCHAR(20) NOT NULL,

                        CONSTRAINT chk_baremo_factor
                            CHECK (factor IN ('S1A', 'S1B', 'S1', 'S2', 'ST')),

                        CONSTRAINT chk_baremo_puntuacion
                            CHECK (puntuacion_directa >= 0),

                        CONSTRAINT chk_baremo_percentil
                            CHECK (percentil BETWEEN 1 AND 99),

                        CONSTRAINT chk_baremo_estado
                            CHECK (estado IN ('ACTIVO', 'INACTIVO'))

    -- Relación definida en el modelo:
    -- version_formulario_id referencia version_formulario(id)
    --
    -- La FK puede agregarse cuando se cree la tabla version_formulario:
    --
    -- ,CONSTRAINT fk_baremo_version_formulario
    --     FOREIGN KEY (version_formulario_id)
    --     REFERENCES version_formulario(id)
);
