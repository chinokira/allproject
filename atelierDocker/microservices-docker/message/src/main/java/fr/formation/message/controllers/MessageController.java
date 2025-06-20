package fr.formation.message.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the message resource.
 */
@RestController
@RequestMapping(value="")
public class MessageController {
	
	String[] messages = {"Salut", "Bonjour", "Hello", "Hi", "Hallo", "Ciao", "Privyèt"};
	
	
	/**
	 * Method returning a list containing all groups. 
	 * @return a list containing all groups.
	 */
	@GetMapping("")
	public String getMessage() {
		return "[v2] " + messages[(int) (Math.random()*(messages.length))];
	}
	
}
