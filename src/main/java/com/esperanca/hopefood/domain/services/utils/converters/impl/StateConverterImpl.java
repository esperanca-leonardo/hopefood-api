package com.esperanca.hopefood.domain.services.utils.converters.impl;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryDto;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.state.StateConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateConverterImpl implements StateConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public State toEntity(StateInputDto stateInputDto) {
		return modelMapper.map(stateInputDto, State.class);
	}

	@Override
	public StateSummaryDto toSummary(State state) {
		return modelMapper.map(state, StateSummaryDto.class);
	}

	@Override
	public StateCompleteDto toCompleteDto(State state) {
		return modelMapper.map(state, StateCompleteDto.class);
	}
}
