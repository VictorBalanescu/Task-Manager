package taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taskManager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByUsername(String username);

    //boolean existsByUsername(String username); // utile per verifica durante la registrazione
}

