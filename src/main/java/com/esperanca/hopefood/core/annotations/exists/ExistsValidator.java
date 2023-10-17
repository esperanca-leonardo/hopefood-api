package com.esperanca.hopefood.core.annotations.exists;

import com.esperanca.hopefood.core.utils.pathhelper.PathHelper;
import com.esperanca.hopefood.core.utils.stringhelper.StringHelper;
import com.esperanca.hopefood.domain.object.services.find.ObjectFinderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

	@Autowired
	private PathHelper pathHelper;

	@Autowired
	private StringHelper stringHelper;

	@Autowired
	private ObjectFinderService objectFinder;

	@Override
	public void initialize(Exists annotation) {
		ConstraintValidator.super.initialize(annotation);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext
			constraintValidatorContext) {
		try {
			requireNonNull(value);

			final Class<?> aClass = value.getClass();

			String className = aClass.getSimpleName();
			className = stringHelper.removeSuffix(className, "IdInput");

			final String entityPath = pathHelper.getEntityPath(className);
			final Class<?> entityClass = Class.forName(entityPath);
			final Field id = aClass.getDeclaredField("id");

			id.setAccessible(true);

			final Long idValue = (Long) id.get(value);
			final Object object = objectFinder.find(idValue, entityClass);

			return !isNull(object);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
