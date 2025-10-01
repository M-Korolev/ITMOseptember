package ru.itmo.javazolotaya.lesson4.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.itmo.javazolotaya.lesson4.api.NotFoundException;
import ru.itmo.javazolotaya.lesson4.api.dto.CityRequest;
import ru.itmo.javazolotaya.lesson4.api.dto.CityResponse;
import ru.itmo.javazolotaya.lesson4.model.City;
import ru.itmo.javazolotaya.lesson4.model.Country;
import ru.itmo.javazolotaya.lesson4.repository.CityRepository;
import ru.itmo.javazolotaya.lesson4.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;

    public CityResponse create(CityRequest req) {
        Country country = countryRepo.findById(req.countryId())
                .orElseThrow(() -> new NotFoundException("СТрана не найдена"));

        City city = City.builder()
                .nameRu(req.nameRu())
                .nameEn(req.nameEn())
                .population(req.population())
                .country(country)
                .build();

        city = cityRepo.save(city);
        return toResp(city);
    }

    @Transactional
    public CityResponse get(Long id) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Город не найден"));
        return toResp(city);
    }

    @Transactional
    public List<CityResponse> list() {
        return cityRepo.findAll().stream().map(this::toResp).toList();
    }

    public CityResponse update(Long id, CityRequest req) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Город не найден"));

        Country country = countryRepo.findById(req.countryId())
                .orElseThrow(() -> new NotFoundException("Cтрана не найдена"));

        city.setNameRu(req.nameRu());
        city.setNameEn(req.nameEn());
        city.setPopulation(req.population());
        city.setCountry(country);

        return toResp(city); // JPA dirty checking
    }

    public void delete(Long id) {
        if (!cityRepo.existsById(id)) {
            throw new NotFoundException("Город не найден");
        }
        cityRepo.deleteById(id);
    }

    private CityResponse toResp(City c) {
        return new CityResponse(
                c.getId(),
                c.getNameRu(),
                c.getNameEn(),
                c.getPopulation(),
                c.getCountry().getId(),
                c.getCountry().getName()
        );
    }
}