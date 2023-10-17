package com.esperanca.hopefood.core.utils.exceptionhelper;

import java.lang.reflect.InvocationTargetException;

public interface ExceptionHelper {

	boolean isNotException(Class<?> classToCheck);

	Exception getExceptionInstance(Class<?> exceptionClass, Long id)
			throws InvocationTargetException, InstantiationException,
			IllegalAccessException, NoSuchMethodException;
}
