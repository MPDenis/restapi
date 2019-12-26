package com.example.controller;


import com.example.domain.DAO.PersonRepository;
import com.example.domain.entity.Person;
import com.example.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persons")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private PersonRepository personRepository;

    public MainController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Person>> getPersons() {
        Iterable<Person> list = personRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") Long personId)
            throws EntityNotFoundException {
        Optional<Person> person = personRepository.findById(personId);
        if (!person.isPresent()) {
            logger.error("getPerson throw " + EntityNotFoundException.class.getName());
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(person.get());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) {
        Person p = personRepository.save(person);
        logger.info("entry added" + person);
        return ResponseEntity.status(201).body(p);
    }

    @PutMapping(value = "/{personId}")
    public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person person,
                                               @PathVariable("personId") Long personId) throws EntityNotFoundException {
        Optional<Person> p = personRepository.findById(personId);
        if (!p.isPresent()) {
            logger.error(EntityNotFoundException.class.getName());
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(personRepository.save(person));
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<Person> deletePerson(@PathVariable("personId") Long personId)
            throws EntityNotFoundException {
        Optional<Person> p = personRepository.findById(personId);
        if (!p.isPresent()) {
            logger.error(EntityNotFoundException.class.getName());
            throw new EntityNotFoundException();
        }
        personRepository.deleteById(personId);
        logger.info("Person " + personId + " deleted");
        return ResponseEntity.ok().body(p.get());
    }
}
