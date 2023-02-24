package fr.dawan.gestionfanclub.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.gestionfanclub.entities.User;
import fr.dawan.gestionfanclub.enums.Role;

@Repository


public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.role =:role")
	List<User> findAllByRole(Role role, Pageable page);

	@Query("SELECT u FROM User u ")
	List<User> findAll();

	@Query("SELECT u FROM User u WHERE u.pseudo =:pseudo")
	User findUserByPseudo(@Param("pseudo") String pseudo);
	
	@Query("SELECT u FROM User u Where u.email =:email")
	User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.id = :id")
	User findUserById(@Param("id") long id);

	@Query("SELECT u FROM User u WHERE u.active =:active")
	List<User> findAllActive(@Param("active") boolean active,Pageable pageable);
	
//	@Query("DELETE FROM User u WHERE u.id =:id")
//	User deleteById(@Param("id") long id);

}