package fr.formation.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import fr.formation.user.models.User;
import fr.formation.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public Mono<User> findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<User> save(@Valid @RequestBody User user) {
        if (user.getId() != null)
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        return userService.save(user);
    }

    @PutMapping("{id}")
    public Mono<User> update(@PathVariable String id, @Valid @RequestBody User user) {
        if (!id.equals(user.getId()))
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        return userService.update(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
    	userService.deleteById(id);
    }
}
