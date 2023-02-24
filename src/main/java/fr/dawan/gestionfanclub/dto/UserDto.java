package fr.dawan.gestionfanclub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private long id;
	
	@NotEmpty
	private String prenom;
	
	@NotEmpty
	private String nom;
	
	@NotEmpty
	private String pseudo;
	
	@NotEmpty(message = "Email should not be empty")
    @Email
	private String email;
	
	@NotEmpty(message = "Password should not be empty")
	private String password;
	
}
