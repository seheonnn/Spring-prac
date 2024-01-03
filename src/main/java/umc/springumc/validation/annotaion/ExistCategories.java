package umc.springumc.validation.annotaion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.springumc.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션
@Constraint(validatedBy = CategoriesExistValidator.class) // CategoriesExistValidator 라는 클래스를 통해 @ExistCategories 가 붙은 대상을 검증.
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) // 어노테이션의 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명 주기를 지정. RUNTIME 은 실행 하는 동안 유효.
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}