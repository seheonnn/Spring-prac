package umc.springumc.validation.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.springumc.apiPayload.code.status.ErrorStatus;
import umc.springumc.domain.Member;
import umc.springumc.service.MemberService.MemberQueryService;
import umc.springumc.validation.annotaion.ExistMember;

@Component
@RequiredArgsConstructor
public class MemberExistsValidator implements ConstraintValidator<ExistMember, Long> {
	private final MemberQueryService memberQueryService;

	@Override
	public void initialize(ExistMember constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Optional<Member> target = memberQueryService.findMember(value);

		if (target.isEmpty()) {
			context.disableDefaultConstraintViolation();
			// MEMBER_NOT_FOUND.toString()
			context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
				.addConstraintViolation();
			return false;
		}
		return true;
	}
}
