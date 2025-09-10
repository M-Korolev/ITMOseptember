package ru.itmo.javazolotaya.lesson2.service.impl;

import org.springframework.stereotype.Component;
import ru.itmo.javazolotaya.lesson2.service.TemperatureConverter;

@Component("celsiusToKelvin")
public class CelsiusToKelvinConverter implements TemperatureConverter {
    @Override
    public double convert(double value) {
        return value + 273.15;
    }
}