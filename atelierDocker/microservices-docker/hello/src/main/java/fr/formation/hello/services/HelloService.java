package fr.formation.hello.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HelloService {
	
	@Value("${services.message.url}")
	private String messageUrl;

	@Value("${services.name.url}")
	private String nameUrl;

	public String getHello() {
		String name = WebClient.create().get().uri(nameUrl + "/random").retrieve().bodyToFlux(String.class)
				.blockFirst();
		String message = WebClient.create().get().uri(messageUrl).retrieve().bodyToFlux(String.class)
				.blockFirst();
		return message + " " + name;
	}

}
