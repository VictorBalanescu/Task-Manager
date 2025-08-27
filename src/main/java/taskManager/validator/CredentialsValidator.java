package taskManager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import taskManager.model.Credentials;
import taskManager.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator{
	
	final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 6;
    
    @Autowired
    CredentialsService credentialsService;
    
    @Override
    public void validate(Object o, Errors errors) {
    	
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

}
