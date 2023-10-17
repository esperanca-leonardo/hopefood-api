package com.esperanca.hopefood.core.annotations.exists;

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

	String message() default "Object not found";
}
