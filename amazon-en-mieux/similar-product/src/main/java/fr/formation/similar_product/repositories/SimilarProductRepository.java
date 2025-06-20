package fr.formation.similar_product.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import fr.formation.similar_product.models.SimilarProduct;

@Repository
public interface SimilarProductRepository extends ReactiveCrudRepository<SimilarProduct, String> {

}
