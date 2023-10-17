package com.esperanca.hopefood.domain.state.services.crud;

import com.esperanca.hopefood.core.interfaces.crud.Crud;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateInput;
import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;
import com.esperanca.hopefood.domain.state.exceptions.StateInUseException;

import java.util.List;

public interface StateCrudService
		extends Crud<Long, StateInput, StateOutput> {

	@Override
	List<StateOutput> findAll();

	@Override
	StateOutput findById(Long id);

	@Override
	StateOutput save(StateInput stateInput);

	@Override
	StateOutput update(Long id, StateInput stateInput);

	@Override
	void deleteById(Long id) throws StateInUseException;
}
