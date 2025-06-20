package fr.formation.registry.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.registry.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private enum GrantType {
        refreshToken,
        password
    }

    private record JwtRequest(
            GrantType grantType,
            String username,
            String password,
            String refreshToken) {

    }

    private record JwtResponse(
            String accessToken,
            String refreshToken) {

    }

    private final AuthenticationService authenticationService;

    @PostMapping("authenticate")
    public Mono<JwtResponse> authenticate(@RequestBody JwtRequest request) {
        return (switch (request.grantType) {
            case password ->
                authenticationService.findPerson(request.username, request.password);
            case refreshToken ->
                authenticationService.findPerson(request.refreshToken);
        }).map(person -> new JwtResponse(
                authenticationService.generateAccessToken(person),
                authenticationService.generateRefreshToken(person)
        ));
    }

}
