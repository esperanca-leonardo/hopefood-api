package com.esperanca.hopefood.domain.state.services.crud;

import com.esperanca.hopefood.core.utils.propertycopier.PropertyCopier;
import com.esperanca.hopefood.domain.state.converters.assemblers.StateOutputAssembler;
import com.esperanca.hopefood.domain.state.converters.disassemblers.StateInputDisassembler;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateInput;
import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;
import com.esperanca.hopefood.domain.state.entities.State;
import com.esperanca.hopefood.domain.state.exceptions.StateInUseException;
import com.esperanca.hopefood.domain.state.repositories.StateRepository;
import com.esperanca.hopefood.domain.state.services.findbyid.StateFindByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateCrudServiceImpl implements StateCrudService {

	@Autowired
	private PropertyCopier copier;

	@Autowired
	private StateRepository repository;

	@Autowired
	private StateFindByIdService service;

	@Autowired
	private StateOutputAssembler assembler;

	@Autowired
	private StateInputDisassembler disassembler;

	@Override
	@Transactional(readOnly = true)
	public List<StateOutput> findAll() {
		final List<State> states = repository.findAll();
		return assembler.toCollectionModel(states);
	}

	@Override
	@Transactional(readOnly = true)
	public StateOutput findById(Long id) {
		return repository.findById(id)
				.map(state -> assembler.toModel(state))
				.orElseGet(StateOutput::new);
	}

	@Override
	@Transactional
	public StateOutput save(StateInput stateInput) {
		State state = disassembler.toEntity(stateInput);
		state = repository.save(state);

		return assembler.toModel(state);
	}

	@Override
	@Transactional
	public StateOutput update(Long id, StateInput stateInput) {
		State state = service.findById(id);

		copier.copyProperties(stateInput, state);
		state = repository.save(state);

		return assembler.toModel(state);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws StateInUseException {
		try {
			repository.deleteById(id);
			repository.flush();
		}
		catch (DataIntegrityViolationException exception) {
			throw new StateInUseException(id);
		}
	}
}
