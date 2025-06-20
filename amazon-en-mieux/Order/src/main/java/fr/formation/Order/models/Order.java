package fr.formation.Order.models;

import java.time.ZonedDateTime;
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
public class Order {
	
    @EqualsAndHashCode.Include
    @Id
    private String id;

    private String user;

    @Builder.Default
    private List<String> products = new ArrayList<String>();

    private ZonedDateTime creationDate;

}
