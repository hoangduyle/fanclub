package fr.dawan.gestionfanclub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.gestionfanclub.entities.User;
import fr.dawan.gestionfanclub.service.IServiceUser;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class ControllerUserRest {

	@Autowired
	private IServiceUser iServiceU;
	
	@GetMapping(value ="/admin", produces = "application/json")
	public List<User> findAllAdmin(Pageable pageable){
		return iServiceU.findAllAdmin(pageable);
	}
	
	@GetMapping(value="/subscriber", produces="application/json")
	public List<User> findAllSubscriber(Pageable pageable){
		return iServiceU.findAllSubscriber(pageable);
	}
	
	@GetMapping(value="/user",produces="application/json")
	public List<User> findAllUser(Pageable pageable){
		return iServiceU.findAllUser(pageable);
	}
	
	@GetMapping(value="/email/{email}",produces="application/json")
	public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
		User u = iServiceU.findUserByEmail(email);
		if(u != null) {
			return ResponseEntity.ok(u);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value="/pseudo/{pseudo}",produces="application/json")
	public ResponseEntity<User> findUserByPseudo(@PathVariable String pseudo) {
		User u = iServiceU.findUserByPseudo(pseudo);
		if(u != null) {
			return ResponseEntity.ok(u);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value="/active", produces = "application/json")
	public List<User> findAllActive(boolean active, Pageable pageable){
		return iServiceU.findAllActive(active, pageable);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		try {
			iServiceU.deleteUser(id);
			return ResponseEntity.ok("L'utilisateur a bien été supprimer");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		try {
			iServiceU.createUser(user);
			return ResponseEntity.ok("L'utilisateur "+user+" a été créer");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(produces ="application/json", consumes="application/json")
	public User updateUser(@RequestBody User user) {
		return iServiceU.updateUser(user);
	}
	
	
	
	
	
}