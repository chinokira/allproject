package fr.formation.message.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

	@Autowired
	MockMvc mvc;


	/**
	 * Check getMessage correctness.
	 */
	@Test
	void getMessage() throws JsonProcessingException, Exception {
		mvc.perform(get("/")
				.contentType(MediaType.TEXT_HTML))
		.andExpect(status().isOk());
	}
}
