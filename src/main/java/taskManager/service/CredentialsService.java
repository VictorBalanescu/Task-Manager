package taskManager.service;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import taskManager.model.Credentials;
import taskManager.repository.CredentialsRepository;

@Service
public class CredentialsService {

    private final PasswordEncoder passwordEncoder;

    private final CredentialsRepository credentialsRepository;

    CredentialsService(CredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		credentials.setRole(Credentials.DefaultRole);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return this.credentialsRepository.save(credentials);
	}
	@Transactional
	public Credentials updateCredentials(Credentials credentials) {
		credentials.setUpdateCreationTime(LocalDateTime.now());
		return this.credentialsRepository.save(credentials);
	}
	@Transactional
	public Credentials getCredentials(String username) {
		return this.credentialsRepository.findByUsername(username);
	}
}
