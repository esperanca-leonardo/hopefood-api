package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryDto;
import com.esperanca.hopefood.domain.exceptions.state.StateInUseException;
import com.esperanca.hopefood.domain.exceptions.state.StateNotFoundException;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.repositories.StateRepository;
import com.esperanca.hopefood.domain.services.utils.converters.impl.StateConverterImpl;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class StateService {

	private StateRepository repository;

	private StateConverterImpl converter;

	@Transactional(readOnly = true)
	public List<StateSummaryDto> findAll() {
		return repository.findAll()
				.stream()
				.map(state -> converter.toSummary(state))
				.toList();
	}

	@Transactional(readOnly = true)
	public State findStateById(Long id) throws StateNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new StateNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public StateCompleteDto findById(Long id) throws StateNotFoundException {
		return repository.findById(id)
				.map(state -> converter.toCompleteDto(state))
				.orElseThrow(() -> new StateNotFoundException(id));
	}

	@Transactional
	public StateCompleteDto save(StateInputDto stateInputDto) {
		State state = converter.toEntity(stateInputDto);
		state = repository.save(state);

		return converter.toCompleteDto(state);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public StateCompleteDto update(StateInputDto stateInputDto, Long id)
			throws StateNotFoundException {

		State state = findStateById(id);
		copyProperties(stateInputDto, state);
		state = repository.save(state);

		return converter.toCompleteDto(state);
	}

	@Transactional
	public void delete(Long id) throws StateNotFoundException, StateInUseException {
		try {
			repository.deleteById(id);
			repository.flush();
		}
		catch (EmptyResultDataAccessException exception) {
			throw new StateNotFoundException(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new StateInUseException(id);
		}
	}
}
