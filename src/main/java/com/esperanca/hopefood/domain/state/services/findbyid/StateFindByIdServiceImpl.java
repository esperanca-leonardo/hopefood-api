package com.esperanca.hopefood.domain.state.services.findbyid;

import com.esperanca.hopefood.domain.city.dtos.inputs.CityInput;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateIdInput;
import com.esperanca.hopefood.domain.state.entities.State;
import com.esperanca.hopefood.domain.state.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StateFindByIdServiceImpl implements StateFindByIdService {

	@Autowired
	private StateRepository repository;

	@Override
	@Transactional(readOnly = true)
	public State findById(Long id) {
		return repository.findById(id)
				.orElseGet(State::new);
	}

	@Override
	@Transactional(readOnly = true)
	public State findById(CityInput cityInput) {
		final Long stateId = getStateId(cityInput);

		return repository.findById(stateId)
				.orElseGet(State::new);
	}

	public Long getStateId(CityInput cityInput) {
		final StateIdInput stateIdInput = cityInput.getState();
		return stateIdInput.getId();
	}
}
