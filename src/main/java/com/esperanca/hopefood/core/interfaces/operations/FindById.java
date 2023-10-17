package com.esperanca.hopefood.core.interfaces.operations;

public interface FindById<ID, Entity> {

	Entity findById(ID id);
}
