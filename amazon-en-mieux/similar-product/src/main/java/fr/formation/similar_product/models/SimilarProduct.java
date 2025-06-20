package fr.formation.similar_product.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@Document
public class SimilarProduct {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    private String productId;

    private int occurrences;
}
