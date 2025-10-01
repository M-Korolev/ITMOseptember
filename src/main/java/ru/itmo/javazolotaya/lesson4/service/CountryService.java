package ru.itmo.javazolotaya.lesson4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.javazolotaya.lesson4.api.NotFoundException;
import ru.itmo.javazolotaya.lesson4.model.Country;
import ru.itmo.javazolotaya.lesson4.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @Transactional
    public Country addCountry(String name) {
        Country country = Country.builder()
                .name(name)
                .build();
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountry(Long id) {
        return countryRepository.findById(id);
    }

    @Transactional
    public Country updateCountry(Long id, String name) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Страна не найдена"));
        country.setName(name);
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllCountries() {
        countryRepository.deleteAll();
    }
}