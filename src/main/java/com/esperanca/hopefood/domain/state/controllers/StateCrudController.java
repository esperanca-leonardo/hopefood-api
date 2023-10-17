package com.esperanca.hopefood.domain.state.controllers;

import com.esperanca.hopefood.core.interfaces.crud.Crud;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateInput;
import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;

import java.util.List;

public interface StateCrudController
		extends Crud<Long, StateInput, StateOutput> {

	@Override
	List<StateOutput> findAll();

	@Override
	StateOutput findById(Long id);

	@Override
	StateOutput save(StateInput input);

	@Override
	StateOutput update(Long id, StateInput input);

	@Override
	void deleteById(Long id);
}
