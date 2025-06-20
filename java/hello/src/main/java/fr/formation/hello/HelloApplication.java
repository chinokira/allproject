package fr.formation.hello;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import fr.formation.hello.models.Person;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class HelloApplication {

    @Value("${registry-api.url}")
    private String registryApiUrl;

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @GetMapping("hello/{id}")
    public Mono<String> sayHello(@PathVariable String id, @Autowired StreamBridge streamBridge) {
        var registryApi = WebClient.builder()
                .baseUrl(registryApiUrl)
                .build();
        return registryApi
                .get()
                .uri("people/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Person.class)
                .map(p -> "Hello " + p.getName())
                .doOnNext(str -> streamBridge.send("send-out-0", str));
    }

}
