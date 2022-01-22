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

    @Override
    public List<List<Person>> splitPersonData(PersonData personData) {
        return ListUtils.partition(personData.getPersons(), personData.getSubListSize());
    }

    @Override
    public List<Person> filterPersonData(PersonData personData) {
        return personData.getPersons()
                .stream()
                .filter(x->x.getAge()>40)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortPersonData(List<Person> persons) {
        Comparator<Person> specificComparator =
                Comparator.comparingInt(Person::getAge)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getLastName);

        return persons.stream()
                .sorted(specificComparator)
                .collect(Collectors.toList());
    }

    @Override
    public void savePersonDataInDatabase(List<Person> persons) {
        System.out.println(persons.get(0));
        personRepository.save(persons.get(0));
    }

    @Override
    public void savePersonDataInFile(List<Person> persons, String path) throws IOException {
        FileUtils.writeStringToFile(new File(path+"data.txt"), persons.toString(),"ISO-8859-1");
    }
}
