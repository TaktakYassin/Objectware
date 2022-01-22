package com.example.Kata.controller;

import com.example.Kata.dto.Person;
import com.example.Kata.dto.PersonData;
import com.example.Kata.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class RestWebController {

    @Value("${path_file}")
    private String path_file;

    @Autowired
    private PersonService personService;

    //This web service can split the body into a list of sublists of person
    @PostMapping("split-persons-list")
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseEntity splitPersonsList(@RequestBody PersonData personData) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PersonData>> constraintViolations = validator.validate(personData);
        if (constraintViolations.isEmpty()) {
            List<List<Person>> personLists = personService.splitPersonData(personData);
            return new ResponseEntity(personLists, null, HttpStatus.OK);
        } else {
            List<String> validationErrors = new ArrayList<>();
            for (ConstraintViolation<PersonData> violation : constraintViolations) {
                validationErrors.add(violation.getPropertyPath() + " : " + violation.getMessage());
            }
            return new ResponseEntity(validationErrors, null, HttpStatus.BAD_REQUEST);
        }
    }

    //This web service is filtering the persons having age superior to 40 ,
    // sorting them by age then by lastname then by first name
    // and finaly saving the data in the table PERSON of a H2 database
    // and into the file data.txt with the specific path in the applciation.properties

    @PostMapping("save-persons")
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseEntity savePersons(@RequestBody PersonData personData) throws IOException {


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PersonData>> constraintViolations = validator.validate(personData);
        if (constraintViolations.isEmpty()) {
            List<Person> personsFiltered = personService.filterPersonData(personData);
            List<Person> personsSorted = personService.sortPersonData(personsFiltered);
            personService.savePersonDataInDatabase(personsSorted);
            personService.savePersonDataInFile(personsSorted, path_file);
            return new ResponseEntity(personsSorted, null, HttpStatus.OK);
        } else {
            List<String> validationErrors = new ArrayList<>();
            for (ConstraintViolation<PersonData> violation : constraintViolations) {
                validationErrors.add(violation.getPropertyPath() + " : " + violation.getMessage());
            }
            return new ResponseEntity(validationErrors, null, HttpStatus.BAD_REQUEST);
        }
    }


    //This web service is recuperating all the persons saved in the table PERSON of the H2 database
    @GetMapping("recuperate-persons")
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseEntity getAllPersons() {
        return new ResponseEntity(personService.getAllPersonsInDatabase(), null, HttpStatus.OK);
    }

}
