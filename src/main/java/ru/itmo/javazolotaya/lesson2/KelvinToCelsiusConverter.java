package ru.itmo.javazolotaya.lesson2;

import org.springframework.stereotype.Component;

@Component("kelvinToCelsius")
public class KelvinToCelsiusConverter implements TemperatureConverter {
    @Override
    public double convert(double value) {
        return value - 273.15;
    }
}