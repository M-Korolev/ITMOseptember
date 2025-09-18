package ru.itmo.javazolotaya.lesson3.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itmo.javazolotaya.lesson3.service.FibonacciService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class FibonacciRunner implements CommandLineRunner {

    private final FibonacciService fibonacciService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите n (или любое число меньше 0 для выхода): ");
            int n = scanner.nextInt();
            if (n < 0) {
                System.out.println("Выход из программы...");
                break;
            }
            long result = fibonacciService.getFibonacci(n);
            System.out.println("F(" + n + ") = " + result);
        }
    }
}
