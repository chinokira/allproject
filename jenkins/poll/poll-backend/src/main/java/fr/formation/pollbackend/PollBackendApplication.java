package fr.formation.pollbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PollBackendApplication {

	@Value("${frontend.url}")
	String frontendUrl;

	public static void main(String[] args) {
		SpringApplication.run(PollBackendApplication.class, args);
	}
	

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "UPDATE", "DELETE", "OPTIONS")
						.allowedOrigins("http://localhost:4200", "http://localhost:3000", frontendUrl);
			}
		};
	}

}
