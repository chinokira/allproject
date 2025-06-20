package fr.formation.registry.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@Document
public class Person {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Length(min = 3)
    private String name;

    @Email
    private String email;

    @Length(min = 8)
    private String password;

    private String phone;

    @Builder.Default
    private List<Address> addresses = new ArrayList<>();
}
