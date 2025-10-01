package ru.itmo.javazolotaya.lesson4.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.javazolotaya.lesson4.api.dto.CityRequest;
import ru.itmo.javazolotaya.lesson4.api.dto.CityResponse;
import ru.itmo.javazolotaya.lesson4.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityResponse> getAllCities() {
        return cityService.list();
    }

    @GetMapping("/{id}")
    public CityResponse getCity(@PathVariable Long id) {
        return cityService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponse createCity(@RequestBody CityRequest request) {
        return cityService.create(request);
    }


    @PutMapping("/{id}")
    public CityResponse updateCity(@PathVariable Long id, @RequestBody CityRequest request) {
        return cityService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
        cityService.delete(id);
    }
}