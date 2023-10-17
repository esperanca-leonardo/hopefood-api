package com.esperanca.hopefood.core.interfaces.operations;

public interface Find<ID, ENTITY> {

	ENTITY find(ID id, Class<?> entityClass);
}
