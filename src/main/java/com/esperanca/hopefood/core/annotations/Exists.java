package com.esperanca.hopefood.core.annotations;

import com.esperanca.hopefood.core.annotations.impl.ExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValidator.class)
public @interface Exists {

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String message() default "Objeto nao encontrado";

	Class<?> targetClass();
}
