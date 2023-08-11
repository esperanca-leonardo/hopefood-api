package com.esperanca.hopefood.core.annotations.impl;

import com.esperanca.hopefood.core.annotations.Exists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

	private Class<?> targetClass;

	@Autowired
	private EntityManager entityManager;

	@Override
	public void initialize(Exists annotation) {
		ConstraintValidator.super.initialize(annotation);
		targetClass = annotation.targetClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext
			constraintValidatorContext) {
		try {
			requireNonNull(value);
			final Class<?> aClass = value.getClass();
			final Field id = aClass.getDeclaredField("id");

			id.setAccessible(true);

			final Long idValue = (Long) id.get(value);
			final Object object = entityManager.find(targetClass, idValue);

			return !isNull(object);
		}
		catch (NoSuchFieldException | IllegalAccessException exception) {
			throw new RuntimeException(exception);
		}
	}
}
