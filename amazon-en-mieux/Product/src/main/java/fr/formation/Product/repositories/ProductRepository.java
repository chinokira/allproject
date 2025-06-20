package fr.formation.Product.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import fr.formation.Product.models.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

}
