package com.example.Kata.service;

import com.example.Kata.dto.Person;
import com.example.Kata.dto.PersonData;

import java.io.IOException;
import java.util.List;

public interface IPersonService {

    List<List<Person>> splitPersonData(PersonData personData);

    List<Person> filterPersonData(PersonData personData);

    List<Person> sortPersonData(List<Person> persons);

    void savePersonDataInDatabase(List<Person> persons);

    void savePersonDataInFile(List<Person> persons, String path) throws IOException;

    List<Person> getAllPersonsInDatabase();

}
