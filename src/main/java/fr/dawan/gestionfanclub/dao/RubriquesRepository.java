package fr.dawan.gestionfanclub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.gestionfanclub.entities.Rubrique;

public interface RubriquesRepository extends JpaRepository<Rubrique, String>{

//	List<Rubrique> findALL(String mot);
//	void create(Rubrique rubrique);
//	void update(Rubrique rubrique);
}
