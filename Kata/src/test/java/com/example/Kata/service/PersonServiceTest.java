package com.example.Kata.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void splitPersonData() {
    }

    @Test
    void filterPersonData() {
    }

    @Test
    void sortPersonData() {
    }

    @Test
    void savePersonDataInDatabase() {
    }

    @Test
    void savePersonDataInFile() {
    }

}