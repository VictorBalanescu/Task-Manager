package taskManager.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import taskManager.model.User;
import taskManager.service.CredentialsService;
import taskManager.model.Credentials;
import taskManager.service.UserService;
import taskManager.validator.CredentialsValidator;

@Controller
public class UserController {

    private final CredentialsService credentialsService;
	
	private final UserService userService;
	
	private CredentialsValidator credentialValidator;
	
	public UserController(UserService userService, CredentialsService credentialsService) {
        this.userService = userService;
        this.credentialsService = credentialsService;
    }

	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
	
	@GetMapping("/register")
    public String showRegisterForm(Model model) {
		model.addAttribute("user",new User());
		model.addAttribute("credentials",new Credentials());
        return "register";
    }
	
	@PostMapping("/register")
	 public String register(@ModelAttribute("credentials") Credentials credentials,BindingResult CredentialsbindingResult,
			 @ModelAttribute("user") User user, BindingResult userbindingResult)  {
		//this.credentialValidator.validate(credentials, CredentialsbindingResult);
		//if(!CredentialsbindingResult.hasErrors()) {
		credentials.setUser(new User(user.getNome(),user.getCognome()));
			credentialsService.saveCredentials(credentials);
			return "redirect:/login";
			
		//}
			
		//return "register";
	}
	
	//@PostMapping("/register")
   // public String registerUser(@RequestParam String username,@RequestParam String password,Model model) {
		
		
        /*boolean success = userService.registerUser(username, password);
        if (success) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username già esistente.");
            return "register";
        }*/
    //}
	
	@GetMapping("/home")
    public String showDashboard(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "home";
    }
	
	@GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
		model.addAttribute("credentials",credentialsService.getCredentials(principal.getName()));
        return "userProfile";
    }
	
	@PostMapping("/modificaUser")
	public String ModificaUser(@RequestParam String nome,@RequestParam String cognome,Principal principal) {
		Credentials credentials = credentialsService.getCredentials(principal.getName());
		credentials.getUser().setCognome(cognome);
		credentials.getUser().setNome(nome);
		credentialsService.updateCredentials(credentials);
		return "redirect:/profile";
	}
	
}
