package taskManager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import taskManager.model.Project;
import taskManager.model.User;
import taskManager.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	protected  ProjectRepository pRepository;
	
	@Transactional
	public Project saveProject(Project project) {
		return this.pRepository.save(project);
	}
	
	@Transactional
	public List<Project> getOwnerProject(User user){
		return this.pRepository.findByOwner(user);
	}
	
	@Transactional
	public Project getProject(long id) {
		Optional<Project> result=this.pRepository.findById(id);
		return result.orElse(null);
	}
	@Transactional
	public void deleteProject(Project project) {
		this.pRepository.delete(project);
	}
}
