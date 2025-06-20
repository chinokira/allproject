package fr.formation.hello.models;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
public class Person {

    @EqualsAndHashCode.Include
    private String id;

    @Length(min = 3)
    private String name;

    @Email
    private String email;

    @Length(min = 8)
    private String password;

    private String phone;
}
