package com.example.Kata.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.Kata.dto.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    List<Person> findAll();

}