package com.esperanca.hopefood.domain.state.converters.disassemblers;

import com.esperanca.hopefood.core.assemblers.Disassembler;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateInput;
import com.esperanca.hopefood.domain.state.entities.State;

public interface StateInputDisassembler extends Disassembler<StateInput, State> {

	@Override
	State toEntity(StateInput stateInput);
}
