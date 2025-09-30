package ru.itmo.javazolotaya.lesson4;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.javazolotaya.lesson4.model.Country;
import ru.itmo.javazolotaya.lesson4.service.CityService;
import ru.itmo.javazolotaya.lesson4.service.CountryService;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class AppRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(AppRunner.class, args);

        CityService cityService = context.getBean(CityService.class);
        CountryService countryService = context.getBean(CountryService.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Добавить город");
            System.out.println("2. Показать все города");
            System.out.println("3. Найти город по ID");
            System.out.println("4. Обновить город");
            System.out.println("5. Удалить город");
            System.out.println("6. Удалить все города");
            System.out.println("7. Показать все страны");
            System.out.println("8. Добавить страну");
            System.out.println("9. Удалить все страны");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Название (RU): ");
                    String ru = scanner.nextLine();
                    System.out.print("Название (EN): ");
                    String en = scanner.nextLine();
                    System.out.print("Население: ");
                    int pop = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID страны: ");
                    long countryId = scanner.nextLong();
                    scanner.nextLine();

                    cityService.addCity(ru, en, pop, countryId);

                    System.out.println("Город добавлен. Текущий список:");
                    cityService.getAllCities().forEach(System.out::println);
                }
                case 2 -> cityService.getAllCities().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Введите ID города: ");
                    long id = scanner.nextLong();
                    System.out.println(cityService.getCity(id));
                }
                case 4 -> {
                    System.out.print("ID города: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Новое название (RU): ");
                    String ru = scanner.nextLine();
                    System.out.print("Новое название (EN): ");
                    String en = scanner.nextLine();
                    System.out.print("Новое население: ");
                    int pop = scanner.nextInt();
                    scanner.nextLine();
                    cityService.updateCity(id, ru, en, pop);
                    System.out.println("Город обновлён. Текущий список:");
                    cityService.getAllCities().forEach(System.out::println);

                }
                case 5 -> {
                    System.out.print("Введите ID: ");
                    long id = scanner.nextLong();
                    cityService.deleteCity(id);
                    System.out.println("Город удалён. Текущий список:");
                    cityService.getAllCities().forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Вы действительно хотите удалить все города? (y/n): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        cityService.deleteAllCities();
                        System.out.println("Все города удалены. Текущий список:");
                        cityService.getAllCities().forEach(System.out::println);
                    } else {
                        System.out.println("Отмена удаления всех городов.");
                    }
                }
                case 7 -> countryService.getAllCountries().forEach(country -> {
                    System.out.println("Страна: " + country.getName() + " (ID=" + country.getId() + ")");
                    if (country.getCities() != null && !country.getCities().isEmpty()) {
                        System.out.println("  Города:");
                        country.getCities().forEach(city ->
                                System.out.println("   - " + city.getNameRu() +
                                        " / " + city.getNameEn() +
                                        " (население: " + city.getPopulation() + ")"));
                    } else {
                        System.out.println("  (Нет городов)");
                    }
                });
                case 8 -> {
                    System.out.print("Название страны: ");
                    String name = scanner.nextLine();
                    Country added = countryService.addCountry(name);
                    System.out.println("Страна добавлена: " + added);
                }
                case 0 -> {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                case 9 -> {
                    System.out.print("Вы действительно хотите удалить все страны? (y/n): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        countryService.deleteAllCountries();
                        System.out.println("Все страны удалены.");
                    } else {
                        System.out.println("Отмена удаления всех стран.");
                    }
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }
}