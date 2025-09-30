package ru.itmo.javazolotaya.lesson4.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.javazolotaya.lesson4.model.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @EntityGraph(attributePaths = "cities")
    List<Country> findAll();
}