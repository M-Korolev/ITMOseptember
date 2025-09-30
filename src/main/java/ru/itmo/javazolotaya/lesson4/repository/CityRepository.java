package ru.itmo.javazolotaya.lesson4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.javazolotaya.lesson4.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
}