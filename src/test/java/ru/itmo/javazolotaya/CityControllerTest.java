package ru.itmo.javazolotaya;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmo.javazolotaya.lesson4.model.Country;
import ru.itmo.javazolotaya.lesson4.repository.CountryRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ru.itmo.javazolotaya.lesson4.AppRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCities() throws Exception {
        mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Autowired
    private CountryRepository countryRepository;

    private Long countryId;

    @BeforeEach
    void setup() {
        // если стран нет в БД то надо добавить
        Country country = countryRepository.findAll().stream().findFirst()
                .orElseGet(() -> countryRepository.save(
                        Country.builder().name("Russia").build()
                ));

        countryId = country.getId();
    }

    @Test
    void testCreateCity() throws Exception {
        String json = """
        {
            "nameRu": "Москва",
            "nameEn": "Moscow",
            "population": 12000000,
            "countryId": %d
        }
    """.formatted(countryId);

        mockMvc.perform(post("/api/cities")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }
}