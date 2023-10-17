package com.esperanca.hopefood.domain.state.services.findbyid;

import com.esperanca.hopefood.core.interfaces.operations.FindById;
import com.esperanca.hopefood.domain.city.dtos.inputs.CityInput;
import com.esperanca.hopefood.domain.state.entities.State;

public interface StateFindByIdService
		extends FindById<CityInput, State> {

	State findById(Long id);

	@Override
	State findById(CityInput cityInput);
}
