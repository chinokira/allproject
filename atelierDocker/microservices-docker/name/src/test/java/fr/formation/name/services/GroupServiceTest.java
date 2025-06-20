package fr.formation.name.services;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootTest
@AutoConfigureMockMvc
class NameControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private NameService ns;

	// Reseting mocks after each use.
	@AfterEach
	void resetMock() {
		reset(ns);
	}

	/**
	 * Check count of GroupService entries.
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 */
	@Test
	void getName_success() throws Exception {
		// state tests
		mvc.perform(get("/")
				.contentType(MediaType.TEXT_HTML))
		.andExpect(status().isOk());

		// behavior tests
		verify(ns, times(1)).getRandomName();
		verifyNoMoreInteractions(ns);
	}
}
