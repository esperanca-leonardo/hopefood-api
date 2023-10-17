package com.esperanca.hopefood.domain.state.converters.assemblers;

import com.esperanca.hopefood.core.beans.modelmapper.ModelMapperProvider;
import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;
import com.esperanca.hopefood.domain.state.entities.State;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateOutputAssemblerImpl extends ModelMapperProvider
		implements StateOutputAssembler {

	@Override
	public StateOutput toModel(State state) {
		return modelMapper.map(state, StateOutput.class);
	}

	@Override
	public List<StateOutput> toCollectionModel(List<State> states) {
		return states.stream()
				.map(this::toModel)
				.toList();
	}
}
