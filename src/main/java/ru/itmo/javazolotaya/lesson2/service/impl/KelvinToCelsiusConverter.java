package ru.itmo.javazolotaya.lesson2.service.impl;

import org.springframework.stereotype.Component;
import ru.itmo.javazolotaya.lesson2.service.TemperatureConverter;

@Component("kelvinToCelsius")
public class KelvinToCelsiusConverter implements TemperatureConverter {
    @Override
    public double convert(double value) {
        return value - 273.15;
    }
}