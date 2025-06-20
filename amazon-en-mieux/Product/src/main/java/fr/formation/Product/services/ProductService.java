package fr.formation.Product.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fr.formation.Product.exceptions.BadRequestException;
import fr.formation.Product.exceptions.NotFoundException;
import fr.formation.Product.models.Product;
import fr.formation.Product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(String id) {
        return productRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("no person with id " + id + " exists")));
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> update(Product product) {
        try {
            findById(product.getId());
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("email already exists", e);
        }
    }

    public void deleteById(String id) {
        productRepository.deleteById(id)
                .doOnSuccess(unused -> System.out.println("Deleted successfully"))
                .doOnError(error -> System.out.println("Error: " + error.getMessage()))
                .subscribe();
    }
}
