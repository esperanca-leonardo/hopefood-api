package com.esperanca.hopefood.core.aspects.entitynotfound;

import com.esperanca.hopefood.core.utils.exceptionhelper.ExceptionHelper;
import com.esperanca.hopefood.core.utils.pathhelper.PathHelper;
import com.esperanca.hopefood.core.utils.reflectionhelper.ReflectionHelper;
import com.esperanca.hopefood.core.utils.stringhelper.StringHelper;
import com.esperanca.hopefood.domain.object.services.find.ObjectFinderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

import static java.lang.Class.forName;
import static java.util.Objects.isNull;

@Aspect
@Component
public class EntityNotFoundAspect {

	@Autowired
	private PathHelper pathHelper;

	@Autowired
	private StringHelper stringHelper;

	@Autowired
	private ExceptionHelper exceptionHelper;

	@Autowired
	private ObjectFinderService objectFinder;

	@Autowired
	private ReflectionHelper reflectionHelper;

	@Autowired
	private EntityManager entityManager;

	@Before("execution(* com.esperanca.hopefood.domain.state.controllers..*(..)) && args(id, ..)")
	public void verifyEntityExistsOrThrow(JoinPoint joinPoint, Long id) throws Exception {
		String className = reflectionHelper.getClassName(joinPoint);
		className = stringHelper.removeSuffix(className, "CrudController");

		final String entityPath = pathHelper.getEntityPath(className);
		final String exceptionPath = pathHelper.getExceptionPath(className, "NotFoundException");

		final Class<?> entityClass = forName(entityPath);
		final Class<?> exceptionClass = forName(exceptionPath);

		if (exceptionHelper.isNotException(exceptionClass)) {
			throw new IllegalArgumentException("Invalid exception class");
		}
		final Object objectFromDb = objectFinder.find(id, entityClass);
		final Exception exceptionInstance = exceptionHelper.getExceptionInstance(exceptionClass, id);


		if (isNull(objectFromDb)) {
			throw exceptionInstance;
		}
		// --------------------------------------------------------------------------------------------
		final List<String> associatedClasses = reflectionHelper.getAssociatedClassNames(entityClass);
		String jpql;
		for (String associatedClass : associatedClasses) {
			String aClass = associatedClass;

			jpql = "SELECT COUNT(c) FROM "+aClass+" c WHERE c."+entityClass.getSimpleName()+".id = :"
		+entityClass.getSimpleName()+"Id";
		}
		Long count = entityManager.createQuery(jpql, Long.class)
				.setParameter("stateId", id)
				.getSingleResult();

		System.out.println(count);
	}
}
