package ru.itmo.javazolotaya.lesson4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.javazolotaya.lesson4.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}