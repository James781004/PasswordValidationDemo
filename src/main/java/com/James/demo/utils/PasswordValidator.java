package com.James.demo.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (!ValidationUtil.checkLength(password)) {
			this.setErrorMessage(context, "Must be between 5 and 12 characters in length.");
			return false;
		}

		if (!ValidationUtil.checkCharacter(password)) {
			this.setErrorMessage(context,
					"Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.");
			return false;
		}

		if (!ValidationUtil.checkSequence(password)) {
			this.setErrorMessage(context,
					"Must not contain any sequence of characters immediately followed by the same sequence.");
			return false;
		}

		return true;
	}

	private void setErrorMessage(ConstraintValidatorContext constraintValidatorContext, String errorMessage) {
		constraintValidatorContext.disableDefaultConstraintViolation();
		constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
	}
}
