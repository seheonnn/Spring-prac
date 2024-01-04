package umc.springumc.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springumc.apiPayload.code.status.ErrorStatus;
import umc.springumc.domain.Store;
import umc.springumc.service.StoreService.StoreQueryService;
import umc.springumc.validation.annotaion.ExistStore;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StoreExistsValidator implements ConstraintValidator<ExistStore, Long> {
    private final StoreQueryService storeQueryService;
    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<Store> target = storeQueryService.findStore(value);

        if (target.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
