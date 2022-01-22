package com.example.Kata.controller;

import com.example.Kata.dto.Person;
import com.example.Kata.dto.PersonData;
import com.example.Kata.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class RestWebController {

    @Autowired
    private PersonService personService;

    @PostMapping("treatData")
    public ResponseEntity treatData(@RequestBody PersonData personData){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PersonData>> constraintViolations = validator.validate(personData);
        if (constraintViolations.isEmpty()){
            List<List<Person>> result = personService.splitPersonData(personData);
            List<Person> result2 = personService.filterPersonData(personData);
            List<Person> result3 = personService.sortPersonData(result2);
            personService.savePersonDataInDatabase(result3);
            return new ResponseEntity(result3, null, HttpStatus.OK);
        }
        else
        {
            List<String> validationErrors = new ArrayList<>();
            for (ConstraintViolation<PersonData> violation : constraintViolations) {
                validationErrors.add(violation.getPropertyPath()+" : "+violation.getMessage());
            }
            return new ResponseEntity(validationErrors, null, HttpStatus.BAD_REQUEST);
        }
    }


}
