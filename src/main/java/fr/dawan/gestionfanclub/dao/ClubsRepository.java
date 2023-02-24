package fr.dawan.gestionfanclub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.gestionfanclub.entities.Club;

public interface ClubsRepository extends JpaRepository<Club , String >{

	List<Club> findAll();
}
