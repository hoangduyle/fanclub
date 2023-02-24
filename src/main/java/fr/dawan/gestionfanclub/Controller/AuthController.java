package fr.dawan.gestionfanclub.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.dawan.gestionfanclub.dto.UserDto;
import fr.dawan.gestionfanclub.entities.User;
import fr.dawan.gestionfanclub.enums.Role;
import fr.dawan.gestionfanclub.service.IServiceUser;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	@Autowired
	private IServiceUser iServiceUser;
	
	@GetMapping("/index")
	public String home() {
		return "index";
	}
	
    @GetMapping("/football")
    public String showFoot() {
    	return"rubrique/football";
    }
    
    @GetMapping("/basket")
    public String showBasket() {
    	return"rubrique/basket";
    }
    
    @GetMapping("/volleyball")
    public String showVolley() {
    	return"rubrique/volleyball";
    }
    @GetMapping("/tennis")
    public String showTennis() {
    	return"rubrique/basket";
    }
   
    @GetMapping("newaccount")
    public String showCreateNewAccountForm(Model model) {
    	UserDto user = new UserDto();
    	model.addAttribute("user",user);
        return "user/new_account_form";
    }
    
    @PostMapping("/newaccount/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = iServiceUser.findUserByPseudo(userDto.getPseudo());

        if(existingUser != null && existingUser.getPseudo() != null && !existingUser.getPseudo().isEmpty()){
            result.rejectValue("pseudo", null, "There is already an account registered with the same pseudo");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/new_account_form";
        }
        User user = new User(0l,0,LocalDate.now(),LocalDate.now(),userDto.getEmail(),userDto.getPrenom(),userDto.getNom(),userDto.getPseudo(),false ,Role.USER, userDto.getPassword(),"", new ArrayList<>());
        iServiceUser.createUser(user);
        return "redirect:/index";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
    	User findedUser = iServiceUser.findUserByEmail(userDto.getEmail());
    	System.out.println(userDto.getPassword());
    	System.out.println(findedUser.getPassword());
    	if(findedUser != null && findedUser.getPassword().equals(userDto.getPassword())) {
    		System.out.println("here it go");
    		return "redirect:/index";
    	}
    	
    	return "user/register_form";
	}
    
    @GetMapping("/register")
    public String showForm() {
        return "user/register_form";
    }
    
    @GetMapping("/admin")
    public String user(Model model){
        List<User> user = iServiceUser.findAllUser(Pageable.unpaged());
        model.addAttribute("user", user);
        return "user/admin";
    }
   
    @GetMapping("/deleteUser")
	public String deleteUser(@RequestParam long id) {
		iServiceUser.deleteUser(id);
		return "redirect:/admin";
	}
    
    
    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model) {
    	User user=iServiceUser.findUserByid(id);
    	UserDto userdto = new UserDto();
    	userdto.setPrenom(user.getPrenom());
    	userdto.setNom(user.getNom());
    	userdto.setEmail(user.getEmail());
    	userdto.setPseudo(user.getPseudo());
    	userdto.setPassword(user.getPassword());
    	userdto.setId(id);
    	
    	model.addAttribute("user",userdto);
        return "user/update";
    }

    @PostMapping("/update")
    public String updatedUser(@Valid @ModelAttribute("user") UserDto userDto,BindingResult result,Model model) {
    	User existingEmail = iServiceUser.findUserByEmail(userDto.getEmail());
		User existingPseudo = iServiceUser.findUserByPseudo(userDto.getPseudo());
	    if(existingPseudo != null && existingPseudo.getPseudo() != null && !existingPseudo.getPseudo().isEmpty() && existingPseudo.getId() != userDto.getId()){
	        result.rejectValue("pseudo", null, "There is already an account registered with the same pseudo");
	    }
	    if(existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isEmpty() && existingEmail.getId() != userDto.getId()){
	        result.rejectValue("email", null, "There is already an account registered with the same email");
	    }
	    if(result.hasErrors())
	    {
	    	System.out.println(result.getAllErrors());
	    	System.out.println(result);
	        model.addAttribute("user", userDto);
	        return "user/update";
	    }
	    User user=iServiceUser.findUserByid(userDto.getId());
	    user.setPrenom(userDto.getPrenom());
	    user.setNom(userDto.getNom());
	    user.setEmail(userDto.getEmail());
	    user.setPseudo(userDto.getPseudo());
	    user.setPassword(userDto.getPassword());

	    iServiceUser.updateUser(user);
	    return "redirect:/admin";
	}

    @GetMapping("/view")
    public String viewUser(@RequestParam Long id, Model model) {
    	User user=iServiceUser.findUserByid(id);
    	UserDto userdto = new UserDto();
    	userdto.setNom(user.getNom());
    	userdto.setPrenom(user.getPrenom());
    	userdto.setEmail(user.getEmail());
    	userdto.setPseudo(user.getPseudo());
    	userdto.setPassword(user.getPassword());
    	userdto.setId(id);
    	
    	model.addAttribute("user",userdto);
        return "user/view";
    }
}

    
    
