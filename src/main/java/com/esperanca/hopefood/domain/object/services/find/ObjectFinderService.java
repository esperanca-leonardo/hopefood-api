package com.esperanca.hopefood.domain.object.services.find;

import com.esperanca.hopefood.core.interfaces.operations.Find;

public interface ObjectFinderService extends Find<Long, Object> {

	@Override
	Object find(Long id, Class<?> entityClass);
}
