package com.esperanca.hopefood.domain.state.converters.disassemblers;

import com.esperanca.hopefood.core.beans.modelmapper.ModelMapperProvider;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateInput;
import com.esperanca.hopefood.domain.state.entities.State;
import org.springframework.stereotype.Component;

@Component
public class StateInputDisassemblerImpl extends ModelMapperProvider
		implements StateInputDisassembler {

	@Override
	public State toEntity(StateInput stateInput) {
		return modelMapper.map(stateInput, State.class);
	}
}
