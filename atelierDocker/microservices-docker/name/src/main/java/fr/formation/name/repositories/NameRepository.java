package fr.formation.name.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.name.models.Name;

@Repository
public interface NameRepository extends JpaRepository<Name, Integer> {
	
	public List<Name> findByName(String name);

}
