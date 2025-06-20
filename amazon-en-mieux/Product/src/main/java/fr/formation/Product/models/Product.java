package fr.formation.Product.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
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
public class Product {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Length(min = 3)
    private String name;

    private float price;

    private String description;

    @Builder.Default
    private List<String> ordres = new ArrayList<>();
}
