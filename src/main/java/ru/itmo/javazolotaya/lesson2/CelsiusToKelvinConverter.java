package ru.itmo.javazolotaya.lesson2;


import org.springframework.stereotype.Component;

@Component("celsiusToKelvin")
public class CelsiusToKelvinConverter implements TemperatureConverter {
    @Override
    public double convert(double value) {
        return value + 273.15;
    }
}