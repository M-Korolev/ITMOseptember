package ru.itmo.javazolotaya.lesson4.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.javazolotaya.lesson4.model.City;
import ru.itmo.javazolotaya.lesson4.model.Country;
import ru.itmo.javazolotaya.lesson4.repository.CityRepository;
import ru.itmo.javazolotaya.lesson4.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Transactional
    public City addCity(String nameRu, String nameEn, int population, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        City city = City.builder()
                .nameRu(nameRu)
                .nameEn(nameEn)
                .population(population)
                .country(country)
                .build();
        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCity(Long id) {
        return cityRepository.findById(id);
    }

    @Transactional
    public City updateCity(Long id, String nameRu, String nameEn, int population) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Город не найден"));
        city.setNameRu(nameRu);
        city.setNameEn(nameEn);
        city.setPopulation(population);
        return cityRepository.save(city);
    }

    @Transactional
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllCities() {
        cityRepository.deleteAll();
    }
}