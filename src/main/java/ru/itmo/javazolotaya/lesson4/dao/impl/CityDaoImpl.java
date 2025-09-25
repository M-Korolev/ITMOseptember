package ru.itmo.javazolotaya.lesson4.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itmo.javazolotaya.lesson4.dao.CityDao;
import ru.itmo.javazolotaya.lesson4.model.City;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CityDaoImpl implements CityDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<City> rowMapper = (rs, rowNum) ->
            new City(
                    rs.getInt("id"),
                    rs.getString("name_ru"),
                    rs.getString("name_en"),
                    rs.getInt("population")
            );

    @Override
    public void insert(City city) {
        jdbcTemplate.update(
                "INSERT INTO cities(name_ru, name_en, population) VALUES (?, ?, ?)",
                city.getNameRu(), city.getNameEn(), city.getPopulation()
        );
    }

    @Override
    public City findById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM cities WHERE id = ?",
                    rowMapper,
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query("SELECT * FROM cities", rowMapper);
    }

    @Override
    public void update(City city) {
        jdbcTemplate.update(
                "UPDATE cities SET name_ru=?, name_en=?, population=? WHERE id=?",
                city.getNameRu(), city.getNameEn(), city.getPopulation(), city.getId()
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM cities WHERE id=?", id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM cities");
    }
}