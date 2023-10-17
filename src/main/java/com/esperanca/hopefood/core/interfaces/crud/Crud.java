package com.esperanca.hopefood.core.interfaces.crud;

import com.esperanca.hopefood.core.interfaces.operations.*;

import java.util.List;

public interface Crud<ID, Input, Output>
		extends FindAll<Output>,
		FindById<ID, Output>,
		Save<Input, Output>,
		Update<ID, Input, Output>,
		DeleteById<ID> {

	@Override
	List<Output> findAll();

	@Override
	Output findById(ID id);

	@Override
	Output save(Input input);

	@Override
	Output update(ID id, Input input);

	@Override
	void deleteById(ID id);
}
