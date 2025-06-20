package fr.formation.Order.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import fr.formation.Order.models.Order;
import fr.formation.Order.services.OrderService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Flux<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("{id}")
    public Mono<Order> findById(@PathVariable String id) {
        return orderService.findById(id);
    }

    @PostMapping
    public Mono<Order> save(@RequestBody Order order) {
        if (order.getId() != null)
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        return orderService.save(order);
    }

    @PutMapping("{id}")
    public Mono<Order> update(@PathVariable String id, @RequestBody Order order) {
        if (!id.equals(order.getId()))
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        return orderService.update(order);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
    	orderService.deleteById(id);
    }
}
