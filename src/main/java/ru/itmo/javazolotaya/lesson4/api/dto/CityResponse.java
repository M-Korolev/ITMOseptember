package ru.itmo.javazolotaya.lesson4.api.dto;

public record CityResponse(
        Long id,
        String nameRu,
        String nameEn,
        Integer population,
        Long countryId,
        String countryName
) {}