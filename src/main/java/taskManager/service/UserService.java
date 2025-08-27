package taskManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskManager.repository.UserRepository;

@Service
public class UserService {
	public UserService() {
		
	}
	@Autowired
	protected  UserRepository userRepository ;
    
	/*@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}*/

}

