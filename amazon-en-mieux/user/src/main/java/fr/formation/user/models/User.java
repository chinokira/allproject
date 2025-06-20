package fr.formation.user.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class User {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    @Builder.Default
    private List<String> orderId = new ArrayList<String>();

}
