package ru.itmo.javazolotaya.lesson2.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itmo.javazolotaya.lesson2.service.TemperatureService;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final TemperatureService service;

    @Override
    public void run(String... args) {
        double input = 25.0;
        double result = service.convertCelsiusToKelvin(input);
        double testReverseConversion = service.convertKelvinToCelsius(result);
        System.out.println(input + " °C = " + result + " K");
        System.out.println(result + " K = " + testReverseConversion + " °C");
    }
}