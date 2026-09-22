CREATE TABLE opcion_reactivo (
                                 id INT PRIMARY KEY,
                                 reactivo_id INT NOT NULL,
                                 literal CHAR(1) NOT NULL,
                                 texto VARCHAR(255),
                                 imagen VARCHAR(255),
                                 orden INT NOT NULL,
                                 es_correcta BOOLEAN NOT NULL

    -- Relación definida en el modelo:
    -- reactivo_id referencia reactivo(id)
    --
    -- La FK puede agregarse cuando se cree la tabla reactivo:
    --
    -- ,CONSTRAINT fk_opcion_reactivo_reactivo
    --     FOREIGN KEY (reactivo_id)
    --     REFERENCES reactivo(id)
);