package fr.formation.registry.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.registry.exceptions.BadRequestException;
import fr.formation.registry.models.Person;
import fr.formation.registry.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("people")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Flux<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("{id}")
    public Mono<Person> findById(@PathVariable String id) {
        return personService.findById(id);
    }

    @PostMapping
    public Mono<Person> save(@Valid @RequestBody Person person) {
        if (person.getId() != null) {
            throw new BadRequestException("id must be null");
        }
        return personService.save(person);
    }

    @PutMapping("{id}")
    public Mono<Person> update(@PathVariable String id, @Valid @RequestBody Person person) {
        if (!id.equals(person.getId())) {
            throw new BadRequestException("ids differ in url and body");
        }
        return personService.update(person);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        personService.deleteById(id);
    }
}
