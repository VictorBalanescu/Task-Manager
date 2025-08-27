package taskManager.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import taskManager.model.Credentials;
import taskManager.model.User;
import taskManager.model.Project;
import taskManager.repository.CredentialsRepository;
import taskManager.service.ProjectService;

@Controller
public class ProjectController {
	@Autowired
	private CredentialsRepository crepository;
	
	@Autowired
	private ProjectService pService;
	
	
	@GetMapping("/newProject")
    public String showProjectForm(Model model) {
		model.addAttribute("project",new Project());
        return "newProject";
    }
	
	@PostMapping("/newProject")
    public String saveProject(@ModelAttribute("project")  Project project,BindingResult bindingResult,Principal principal) {
        //if (bindingResult.hasErrors()) {
           // return "projects/form";
       // }

        Credentials credentials = crepository.findByUsername(principal.getName());
        project.setOwner(credentials.getUser());
        pService.saveProject(project);
        return "redirect:/project/"+project.getId();
    }
	
	@GetMapping("/myProjects")
    public String showProjects(Model model, Principal principal,@RequestParam(defaultValue = "0") int page) {
		Credentials credentials = crepository.findByUsername(principal.getName());
		List<Project> progetti = pService.getOwnerProject(credentials.getUser());
		model.addAttribute("username", principal.getName());
		model.addAttribute("projectsList",progetti);
        return "myProjects";
    }
	
	@PostMapping("/delete/project/{id}")
	public String deleteProject(@PathVariable("id") Long id, Principal principal,Model model) {
		Project project= pService.getProject(id);
		pService.deleteProject(project);
		Credentials credentials = crepository.findByUsername(principal.getName());
		List<Project> progetti = pService.getOwnerProject(credentials.getUser());
		model.addAttribute("projectsList",progetti);
		model.addAttribute("username", principal.getName());
		return "redirect:/myProjects";
	}
	@GetMapping("/project/{id}")
	public String project(@PathVariable("id") Long id,Model model) {
		Project project=pService.getProject(id);
		project.addMembers(new User("luca","luca"));
		project.addMembers(new User("vic","vic"));
		model.addAttribute("project",project);
		return "project";
	}
	@PostMapping("")
	public String updateProject() {
		return "";
	}
	
	
}
