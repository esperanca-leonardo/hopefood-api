package com.esperanca.hopefood.domain.object.services.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ObjectFinderServiceImpl implements ObjectFinderService {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Object find(Long id, Class<?> entityClass) {
		return entityManager.find(entityClass, id);
	}
}
