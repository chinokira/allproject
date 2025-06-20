package fr.formation.registry.services;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.registry.exceptions.BadRequestException;
import fr.formation.registry.exceptions.NotFoundException;
import fr.formation.registry.models.Person;
import fr.formation.registry.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    public Mono<Person> findById(String id) {
        return personRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("no person with id " + id + " exists")));
    }

    public Mono<Person> save(Person person) {
        try {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            return personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("email already exists", e);
        }
    }

    public Mono<Person> update(Person person) {
        try {
            findById(person.getId());
            return personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("email already exists", e);
        }
    }

    public void deleteById(String id) {
        findById(id);
        personRepository.deleteById(id);
    }
}
