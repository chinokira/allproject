package fr.formation.user.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import fr.formation.user.models.User;
import fr.formation.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(String id) {
        return userRepository
            .findById(id)
            .switchIfEmpty(Mono.error(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {}));
    }

    public Mono<User> save(User user) {
        try {
        	user.setPassword(user.getPassword());
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        }
    }

    public Mono<User> update(User user) {
        try {
            findById(user.getId());
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        }
    }

    public void deleteById(String id) {
        findById(id);
        userRepository.deleteById(id)
        .doOnSuccess(unused -> System.out.println("Deleted successfully"))
        .doOnError(error -> System.out.println("Error: " + error.getMessage()))
        .subscribe();
    }

}
