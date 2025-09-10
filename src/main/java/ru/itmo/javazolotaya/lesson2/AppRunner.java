package ru.itmo.javazolotaya.lesson2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final TemperatureService service;

    public AppRunner(TemperatureService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        double input = 25.0;
        double result = service.convertCelsiusToKelvin(input);
        double testReverseConversion = service.convertKelvinToCelsius(result);
        System.out.println(input + " °C = " + result + " K");
        System.out.println(result + " K = " + testReverseConversion + " °C");
    }
}