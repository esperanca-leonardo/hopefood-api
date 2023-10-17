package com.esperanca.hopefood.core.utils.reflectionhelper;

import com.esperanca.hopefood.core.utils.stringhelper.StringHelper;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Component
public class ReflectionHelperImpl implements ReflectionHelper {

	@Autowired
	private StringHelper stringHelper;

	@Override
	public String getGenericTypeName(Field field) {
		final Type genericType = field.getGenericType();
		return genericType.getTypeName();
	}

	@Override
	public String getClassName(JoinPoint joinPoint) {
		final Object targetObject = joinPoint.getTarget();
		final Class<?> aClass = targetObject.getClass();

		return aClass.getSimpleName();
	}

	@Override
	public List<String> getAssociatedClassNames(Class<?> entityClassToInspect) {
		return stream(entityClassToInspect.getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(OneToMany.class))
				.map(this::getGenericTypeName)
				.map(fieldTypeName -> stringHelper.extractRegex(fieldTypeName, "entities\\.(.*?)>"))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.toList();
	}
}
