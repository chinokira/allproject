package fr.formation.name.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.name.models.Name;
import fr.formation.name.repositories.NameRepository;

@Service
public class NameService {

	@Autowired
	private NameRepository nr;
	
	public String getRandomName() {
		List<Name> names = nr.findAll();
		if (names.size() > 0)
			return names.get((int) (Math.random()*(names.size()))).getName();
		return "[No names in BD]";
	}
	
	public List<String> findAll() {
		return nr.findAll().stream()
				.map(n -> n.getName())
				.collect(Collectors.toList());
	}
	
	public void save(String name) {
		nr.save(new Name().setName(name));
	}
	
	public void delete(String name) {
		nr.findByName(name).forEach(n -> nr.delete(n));
	}
	
}
