package com.esperanca.hopefood.domain.services.utils.propertycopiers.impl;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.services.utils.propertycopiers.interfaces.StatePropertyCopier;
import com.esperanca.hopefood.domain.services.shared.AbstractMapperProvider;
import org.springframework.stereotype.Component;

@Component
public class StatePropertyCopierImpl extends AbstractMapperProvider
		implements StatePropertyCopier {

	@Override
	public void copyProperties(StateInputDto stateInputDto, State state) {
		modelMapper.map(stateInputDto, state);
	}
}
