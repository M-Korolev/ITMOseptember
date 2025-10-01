package ru.itmo.javazolotaya.lesson4.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CityRequest(
        @NotBlank String nameRu,
        @NotBlank String nameEn,
        @NotNull @PositiveOrZero Integer population,
        @NotNull Long countryId
) {}