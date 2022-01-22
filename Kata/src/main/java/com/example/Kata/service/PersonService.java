package com.example.Kata.service;

import com.example.Kata.dao.PersonRepository;
import com.example.Kata.dto.Person;
import com.example.Kata.dto.PersonData;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    //This method is spliting the body into a list of sublists of person
    @Override
    public List<List<Person>> splitPersonData(PersonData personData) {
        return ListUtils.partition(personData.getPersons(), personData.getSubListSize());
    }

    //This method is filtering the persons having an age superior to 40
    @Override
    public List<Person> filterPersonData(PersonData personData) {
        return personData.getPersons()
                .stream()
                .filter(x -> x.getAge() > 40)
                .collect(Collectors.toList());
    }

    //This method is sorting the list of persons by age then by lastname then by first name
    @Override
    public List<Person> sortPersonData(List<Person> persons) {
        Comparator<Person> specificComparator =
                Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getLastName)
                        .thenComparing(Person::getFirstName);

        return persons.stream()
                .sorted(specificComparator)
                .collect(Collectors.toList());
    }

    //This method is saving all the persons in the table person of the h2 database
    @Override
    public void savePersonDataInDatabase(List<Person> persons) {
        personRepository.saveAll(persons);
    }

    //This method is recuperating all the persons saved in the table PERSON of the H2 database
    @Override
    public List<Person> getAllPersonsInDatabase() {
        return personRepository.findAll();
    }

    //This method is saving the persons data into the file data.txt with the specific path in the applciation.properties
    @Override
    public void savePersonDataInFile(List<Person> persons, String path) throws IOException {
        FileUtils.writeStringToFile(new File(path + "data.txt"), persons.toString(), "ISO-8859-1");
    }
}
