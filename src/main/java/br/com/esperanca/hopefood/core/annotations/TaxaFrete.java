package br.com.esperanca.hopefood.core.annotations;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@NotNull
@PositiveOrZero
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE}
)
public @interface TaxaFrete {

  String message() default "O valor informado deve ser maior ou igual a zero";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
