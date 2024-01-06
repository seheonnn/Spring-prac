package umc.springumc.validation.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.springumc.apiPayload.code.status.ErrorStatus;
import umc.springumc.repository.FoodCategoryRepository;
import umc.springumc.validation.annotaion.ExistCategories;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

	private final FoodCategoryRepository foodCategoryRepository;

	@Override
	public void initialize(ExistCategories constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	// isValid 에서 false 가 반환되면 ConstraintViolationException 이 반환되는데,
	// @Valid 가 있어 예외가 바로 전달되지 않고 MethodArgumentNotValidException 발생.
	// MethodArgumentNotValidException 을 ExceptionAdvice 에서 잡아 처리
	public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
		//        boolean isValid = values.stream()
		//                .allMatch(value -> foodCategoryRepository.existsById(value)); // Repository 접근은 Service 에서만 하는 것이 좋음

		boolean isValid = values.stream()
			.allMatch(value -> foodCategoryRepository.existsById(value));

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			// FOOD_CATEGORY_NOT_FOUND.getMessage()
			context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.getMessage())
				.addConstraintViolation();
		}

		return isValid;

	}
}
