package com.esperanca.hopefood.core.utils.reflectionhelper;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.util.List;

public interface ReflectionHelper {

	String getGenericTypeName(Field field);

	String getClassName(JoinPoint joinPoint);

	List<String> getAssociatedClassNames(Class<?> entityClassToInspect);
}
