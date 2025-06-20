package fr.formation.registry.services;

import java.util.UUID;
import java.util.function.UnaryOperator;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import fr.formation.registry.models.Person;
import fr.formation.registry.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public Mono<Person> findPerson(String email, String password) {
        return personRepository.findByEmail(email)
                .filter(p -> passwordEncoder.matches(password, p.getPassword()))
                .switchIfEmpty(Mono.error(new BadCredentialsException("invalid email or password")));
    }

    public Mono<Person> findPerson(String refreshToken) {
        final var jwt = jwtDecoder.decode(refreshToken);
        return personRepository.findById(jwt.getSubject())
                .switchIfEmpty(Mono.error(new BadCredentialsException("invalid refresh token")));
    }

    public String generateAccessToken(Person person) {
        return generateToken(
                person,
                Duration.ofMinutes(30),
                b -> b.claim("name", person.getName())
        );
    }

    public String generateRefreshToken(Person person) {
        return generateToken(person, Duration.ofHours(1));
    }

    private String generateToken(Person person, TemporalAmount validity, UnaryOperator<JwtClaimsSet.Builder> claimsConfigurer) {
        var builder = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(validity))
                .subject(person.getId());
        builder = claimsConfigurer.apply(builder);
        JwtClaimsSet claims = builder.build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String generateToken(Person person, TemporalAmount validity) {
        return generateToken(person, validity, b -> b);
    }
}
