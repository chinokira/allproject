package fr.formation.name.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.name.services.NameService;

/**
 * Rest controller for the message resource.
 */
@RestController
@RequestMapping(value="")
public class NameController {
	
	@Autowired
	private NameService ns;
	
	/**
	 * Method returning a name chosen randomly 
	 * @return a name.
	 */
	@GetMapping("random")
	public String getRandomName() {
		return ns.getRandomName();
	}

	/**
	 * Method returning a list containing all names. 
	 * @return a list containing all names.
	 */
	@GetMapping("")
	public List<String> findAll() {
		return ns.findAll();
	}

	/**
	 * Method returning a list containing all groups. 
	 * @return a list containing all groups.
	 */
	@PostMapping("")
	public void save(@RequestBody String name) {
		ns.save(name);
	}

	/**
	 * Method returning a list containing all groups. 
	 * @return a list containing all groups.
	 */
	@DeleteMapping("{name}")
	public void deleteName(@PathVariable("name") String name) {
		ns.delete(name);
	}
	
}
