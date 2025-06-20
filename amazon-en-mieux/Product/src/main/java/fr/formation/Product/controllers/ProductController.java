package fr.formation.Product.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.Product.exceptions.BadRequestException;
import fr.formation.Product.models.Product;
import fr.formation.Product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Mono<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping
    public Mono<Product> save(@Valid @RequestBody Product product) {
        if (product.getId() != null) {
            throw new BadRequestException("id must be null");
        }
        return productService.save(product);
    }

    @PutMapping("{id}")
    public Mono<Product> update(@PathVariable String id, @Valid @RequestBody Product product) {
        if (!id.equals(product.getId())) {
            System.out.println(product.getId());
            throw new BadRequestException("ids differ in url and body");
        }
        return productService.update(product);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        productService.deleteById(id);
    }
}
