package com.esperanca.hopefood.domain.state.converters.assemblers;

import com.esperanca.hopefood.core.assemblers.Assembler;
import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;
import com.esperanca.hopefood.domain.state.entities.State;

import java.util.List;

public interface StateOutputAssembler extends Assembler<State, StateOutput> {

	@Override
	StateOutput toModel(State state);

	@Override
	List<StateOutput> toCollectionModel(List<State> states);
}
