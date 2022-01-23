package com.example.Kata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.Kata.dto.Person;
import com.example.Kata.dto.PersonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    private final PersonService personService = mock(PersonService.class);
    private PersonData personData;

    @Test
    public void test_splitPersonData_expect_three_sublists() throws IOException {
        personData = new ObjectMapper().readValue(loadFileAsString("personsData.json"), PersonData.class);
        when(personService.splitPersonData(any())).thenCallRealMethod();
        List<List<Person>> result = personService.splitPersonData(personData);
        assertEquals(result.size(), 3);
    }

    @Test
    void test_filterPersonData_expect_two_persons() throws IOException {
        personData = new ObjectMapper().readValue(loadFileAsString("personsData.json"), PersonData.class);
        when(personService.filterPersonData(any())).thenCallRealMethod();
        List<Person> result = personService.filterPersonData(personData);
        assertEquals(result.size(), 2);
    }

    @Test
    void test_sortPersonData_expect_order_change() throws IOException {
        personData = new ObjectMapper().readValue(loadFileAsString("personsData.json"), PersonData.class);
        when(personService.filterPersonData(any())).thenCallRealMethod();
        List<Person> filtredPersons = personService.filterPersonData(personData);
        when(personService.sortPersonData(any())).thenCallRealMethod();
        List<Person> sortedPersons = personService.sortPersonData(filtredPersons);
        //after the sort method , the order of the two persons should change because the age of the second person is inferior to the first one
        assertEquals(filtredPersons.get(0),sortedPersons.get(1));
        assertEquals(filtredPersons.get(1),sortedPersons.get(0));
    }

    @Test
    void savePersonDataInFile() throws IOException {
        personData = new ObjectMapper().readValue(loadFileAsString("personsData.json"), PersonData.class);
        List<Person> persons = personData.getPersons();
        new PersonService().savePersonDataInFile(personData.getPersons(),"src/test/resources/");
        Assertions.assertTrue(loadFileAsString("data.txt").contains(persons.toString()));
    }

    private static String loadFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = PersonServiceTest.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

}