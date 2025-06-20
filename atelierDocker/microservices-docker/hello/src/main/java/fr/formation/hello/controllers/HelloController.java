package fr.formation.hello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.hello.services.HelloService;

/**
 * Rest controller for the message resource.
 */
@RestController
@RequestMapping(value="")
public class HelloController {
	
	@Autowired
	HelloService hs;
	
	@GetMapping("")
	public String getHello() {
		return hs.getHello();
	}
	
}
