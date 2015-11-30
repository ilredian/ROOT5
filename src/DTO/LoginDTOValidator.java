package DTO;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginDTOValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz){
		return LoginDTO.class.isAssignableFrom(clazz);
	}
	
	
	@Override
	public void validate(Object target, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmpty(errors, "pwd", "required");
		
	}

	

}
