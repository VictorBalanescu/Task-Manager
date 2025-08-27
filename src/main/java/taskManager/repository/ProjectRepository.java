package taskManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import taskManager.model.Project;
import taskManager.model.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	Project save(Project project);

	List<Project> findByOwner(User user);


}
