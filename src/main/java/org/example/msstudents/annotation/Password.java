package org.example.msstudents.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = PasswordValidator.class)

public @interface Password {
    String message() default "Şifrənin uzunluğu minimum 8 olmalıdır. Daxilində bir kiçik hərf bir böyük hərf bir xüsusi simvol və bir rəqəm olmalıdır";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
