package com.esperanca.hopefood.domain.services.utils.converters.interfaces.state;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryDto;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics.Converter;

public interface StateConverter extends Converter<State, StateInputDto,
		StateSummaryDto, StateCompleteDto> {
}
