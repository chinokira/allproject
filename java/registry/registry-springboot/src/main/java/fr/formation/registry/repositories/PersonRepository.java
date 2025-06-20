package fr.formation.registry.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import fr.formation.registry.models.Person;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

    Mono<Person> findByEmail(String email);
}
