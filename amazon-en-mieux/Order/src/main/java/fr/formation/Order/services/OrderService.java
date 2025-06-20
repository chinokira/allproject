package fr.formation.Order.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import fr.formation.Order.models.Order;
import fr.formation.Order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> findById(String id) {
        return orderRepository
            .findById(id)
            .switchIfEmpty(Mono.error(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {}));
    }

    public Mono<Order> save(Order order) {
        try {
            return orderRepository.save(order);
        } catch (DataIntegrityViolationException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        }
    }

    public Mono<Order> update(Order order) {
        try {
            findById(order.getId());
            return orderRepository.save(order);
        } catch (DataIntegrityViolationException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {};
        }
    }

    public void deleteById(String id) {
        findById(id);
        orderRepository.deleteById(id)
        .doOnSuccess(unused -> System.out.println("Deleted successfully"))
        .doOnError(error -> System.out.println("Error: " + error.getMessage()))
        .subscribe();
    }

}
