package ru.itmo.javazolotaya.lesson4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    private Integer id;
    private String nameRu;
    private String nameEn;
    private int population;
}