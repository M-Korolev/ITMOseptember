package ru.itmo.javazolotaya.lesson2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {

    private final TemperatureConverter celsiusToKelvinConverter;
    private final TemperatureConverter kelvinToCelsiusConverter;

    public TemperatureService(
            @Qualifier("celsiusToKelvin") TemperatureConverter celsiusToKelvinConverter,
            @Qualifier("kelvinToCelsius") TemperatureConverter kelvinToCelsiusConverter
    ) {
        this.celsiusToKelvinConverter = celsiusToKelvinConverter;
        this.kelvinToCelsiusConverter = kelvinToCelsiusConverter;
    }

    public double convertCelsiusToKelvin(double value) {
        return celsiusToKelvinConverter.convert(value);
    }

    public double convertKelvinToCelsius(double value) {
        return kelvinToCelsiusConverter.convert(value);
    }
}