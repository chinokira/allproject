package fr.formation.pollbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.formation.pollbackend.repositories.PollRepository;
import fr.formation.pollbackend.repositories.UserRepository;

@SpringBootTest
class PollBackendApplicationTests {

  @MockBean
  private PollRepository pollRepository;
  @MockBean
  private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

}
