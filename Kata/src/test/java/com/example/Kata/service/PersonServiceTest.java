package com.example.Kata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.Kata.dto.Person;
import com.example.Kata.dto.PersonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    private PersonService personService = mock(PersonService.class);

    @Test
    @Ignore
    public void splitPersonData() throws IOException {
        PersonData personData = new ObjectMapper().readValue(loadFileAsString("personsData.json"), PersonData.class);
        List<List<Person>> result = personService.splitPersonData(personData);
        assertEquals(result.size(), 6);
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

    private static String loadFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = new PersonServiceTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

}