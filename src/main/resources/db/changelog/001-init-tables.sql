--liquibase formatted sql

--changeset you:001-create-countries-and-cities
CREATE TABLE IF NOT EXISTS countries (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS cities (
    id BIGSERIAL PRIMARY KEY,
    name_ru VARCHAR(100) NOT NULL,
    name_en VARCHAR(100) NOT NULL,
    population INT NOT NULL,
    country_id BIGINT NOT NULL REFERENCES countries(id)
);
--rollback DROP TABLE IF EXISTS cities; DROP TABLE IF EXISTS countries;