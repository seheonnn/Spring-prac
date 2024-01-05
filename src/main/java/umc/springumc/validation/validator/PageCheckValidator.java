package umc.springumc.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.springumc.apiPayload.code.status.ErrorStatus;
import umc.springumc.validation.annotaion.CheckPage;

public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {
	@Override
	public void initialize(CheckPage constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		if (value < 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_OUT_OF_RANGE.toString())
				.addConstraintViolation();
			return false;
		}
		return true;
	}
}
