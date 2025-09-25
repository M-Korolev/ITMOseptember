package ru.itmo.javazolotaya.lesson4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.javazolotaya.lesson4.model.City;
import ru.itmo.javazolotaya.lesson4.service.CityService;

import java.util.Scanner;

@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(AppRunner.class, args);

        CityService service = context.getBean(CityService.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Добавить город");
            System.out.println("2. Показать все города");
            System.out.println("3. Найти город по ID");
            System.out.println("4. Обновить город");
            System.out.println("5. Удалить город");
            System.out.println("6. Удалить все города");
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

                    City city = City.builder()
                            .nameRu(ru)
                            .nameEn(en)
                            .population(pop)
                            .build();

                    service.addCity(city);

                    System.out.println("Город добавлен. Текущий список:");
                    service.getAllCities().forEach(System.out::println);
                }
                case 2 -> service.getAllCities().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    System.out.println(service.getCity(id));
                }
                case 4 -> {
                    System.out.print("ID города: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Новое название (RU): ");
                    String ru = scanner.nextLine();
                    System.out.print("Новое название (EN): ");
                    String en = scanner.nextLine();
                    System.out.print("Новое население: ");
                    int pop = scanner.nextInt();
                    scanner.nextLine();
                    service.updateCity(new City(id, ru, en, pop));
                    System.out.println("Город обновлён. Текущий список:");
                    service.getAllCities().forEach(System.out::println);

                }
                case 5 -> {
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    service.deleteCity(id);
                    System.out.println("Город удалён. Текущий список:");
                    service.getAllCities().forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("⚠ Вы действительно хотите удалить все города? (y/n): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        service.deleteAllCities();
                        System.out.println("🗑 Все города удалены. Текущий список:");
                        service.getAllCities().forEach(System.out::println);
                    } else {
                        System.out.println("Отмена удаления всех городов.");
                    }
                }
                case 0 -> {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }
}