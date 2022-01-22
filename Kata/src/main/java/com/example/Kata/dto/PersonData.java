package com.example.Kata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.List;

public class PersonData {

    @Valid
    @JsonProperty("Persons")
    private List<Person> persons;
    @JsonProperty("SubListSize")
    private int subListSize;

    public PersonData() {
    }

    public PersonData(List<Person> persons, int subListSize) {
        this.persons = persons;
        this.subListSize = subListSize;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getSubListSize() {
        return subListSize;
    }

    public void setSubListSize(int subListSize) {
        this.subListSize = subListSize;
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "persons=" + persons +
                ", subListSize=" + subListSize +
                '}';
    }
}
