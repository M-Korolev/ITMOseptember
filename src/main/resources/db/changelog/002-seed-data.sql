--liquibase formatted sql

--changeset you:002-seed-countries
-- ensure uniqueness constraint
ALTER TABLE countries ADD CONSTRAINT IF NOT EXISTS unique_country_name UNIQUE (name);

INSERT INTO countries (name) VALUES ('Russia')
    ON CONFLICT DO NOTHING;

INSERT INTO countries (name) VALUES ('Germany')
    ON CONFLICT DO NOTHING;

INSERT INTO countries (name) VALUES ('USA')
    ON CONFLICT DO NOTHING;
--rollback DELETE FROM countries WHERE name IN ('Russia','Germany','USA');

--changeset you:003-seed-cities
INSERT INTO cities (name_ru, name_en, population, country_id)
VALUES ('Санкт-Петербург', 'Saint Petersburg', 5600000,
       (SELECT id FROM countries WHERE name='Russia' LIMIT 1))
ON CONFLICT DO NOTHING;

INSERT INTO cities (name_ru, name_en, population, country_id)
VALUES ('Берлин', 'Berlin', 3700000,
       (SELECT id FROM countries WHERE name='Germany' LIMIT 1))
ON CONFLICT DO NOTHING;

INSERT INTO cities (name_ru, name_en, population, country_id)
VALUES ('Нью-Йорк', 'New York', 8400000,
       (SELECT id FROM countries WHERE name='USA' LIMIT 1))
ON CONFLICT DO NOTHING;
--rollback DELETE FROM cities WHERE name_en IN ('Saint Petersburg','Berlin','New York');