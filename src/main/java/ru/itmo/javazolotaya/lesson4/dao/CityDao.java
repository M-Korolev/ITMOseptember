package ru.itmo.javazolotaya.lesson4.dao;

import ru.itmo.javazolotaya.lesson4.model.City;

import java.util.List;

public interface CityDao {
    void insert(City city);

    City findById(int id);

    List<City> findAll();

    void update(City city);

    void delete(int id);

    void deleteAll();
}