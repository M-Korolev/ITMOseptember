package ru.itmo.javazolotaya.lesson4.service;

import org.springframework.stereotype.Service;
import ru.itmo.javazolotaya.lesson4.dao.CityDao;
import ru.itmo.javazolotaya.lesson4.model.City;

import java.util.List;

@Service
public class CityService {

    private final CityDao cityDao;

    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public void addCity(City city) {
        cityDao.insert(city);
    }

    public City getCity(int id) {
        return cityDao.findById(id);
    }

    public List<City> getAllCities() {
        return cityDao.findAll();
    }

    public void updateCity(City city) {
        cityDao.update(city);
    }

    public void deleteCity(int id) {
        cityDao.delete(id);
    }

    public void deleteAllCities() {
        cityDao.deleteAll();
    }
}