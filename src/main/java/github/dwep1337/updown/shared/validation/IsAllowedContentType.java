package github.dwep1337.updown.shared.validation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;

@Constraint(validatedBy = FileValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsAllowedContentType {
    String message() default "File type is not allowed";
    Class<?>[] groups() default {};
    Class<?> [] payload() default {};
}