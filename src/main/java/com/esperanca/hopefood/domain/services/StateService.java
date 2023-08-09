package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateCompleteOutputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryOutputDto;
import com.esperanca.hopefood.domain.exceptions.state.StateInUseException;
import com.esperanca.hopefood.domain.exceptions.state.StateNotFoundException;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.repositories.StateRepository;
import com.esperanca.hopefood.domain.services.converters.GenericConverter;
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

	private StateRepository stateRepository;

	private GenericConverter<State, StateCompleteOutputDto, StateInputDto,
			StateSummaryOutputDto> stateConverter;

	private State convertToEntity(StateInputDto stateInputDto) {
		return stateConverter.convertToEntity(stateInputDto, State.class);
	}

	private StateSummaryOutputDto convertToSummaryDto(State state) {
		return stateConverter.convertToSummaryDto(state, StateSummaryOutputDto.class);
	}

	private StateCompleteOutputDto convertToCompleteDto(State state) {
		return stateConverter.convertToCompleteDto(state, StateCompleteOutputDto.class);
	}

	@Transactional(readOnly = true)
	public List<StateSummaryOutputDto> findAll() {
		return stateRepository.findAll()
				.stream()
				.map(this::convertToSummaryDto)
				.toList();
	}

	@Transactional(readOnly = true)
	public State findStateById(Long id) throws StateNotFoundException {
		return stateRepository.findById(id)
				.orElseThrow(() -> new StateNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public StateCompleteOutputDto findById(Long id) throws StateNotFoundException {
		return stateRepository.findById(id)
				.map(this::convertToCompleteDto)
				.orElseThrow(() -> new StateNotFoundException(id));
	}

	@Transactional
	public StateCompleteOutputDto save(StateInputDto stateInputDto) {
		State state = convertToEntity(stateInputDto);
		state = stateRepository.save(state);

		return convertToCompleteDto(state);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public StateCompleteOutputDto update(StateInputDto stateInputDto, Long id)
			throws StateNotFoundException {

		State state = findStateById(id);
		copyProperties(stateInputDto, state);
		state = stateRepository.save(state);

		return convertToCompleteDto(state);
	}

	@Transactional
	public void delete(Long id) throws StateNotFoundException, StateInUseException {
		try {
			stateRepository.deleteById(id);
			stateRepository.flush();
		}
		catch (EmptyResultDataAccessException exception) {
			throw new StateNotFoundException(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new StateInUseException(id);
		}
	}
}
