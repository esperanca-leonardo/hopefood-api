package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryDto;
import com.esperanca.hopefood.domain.exceptions.state.StateInUseException;
import com.esperanca.hopefood.domain.exceptions.state.StateNotFoundException;
import com.esperanca.hopefood.domain.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateService service;

	@GetMapping
	@ResponseStatus(OK)
	public List<StateSummaryDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public StateCompleteDto findById(@PathVariable Long id)
			throws StateNotFoundException {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public StateCompleteDto save(@RequestBody @Valid StateInputDto stateInputDto) {
		return service.save(stateInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public StateCompleteDto update(@RequestBody @Valid StateInputDto
			stateInputDto, @PathVariable Long id) throws StateNotFoundException {
		return service.update(stateInputDto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws
			StateNotFoundException, StateInUseException {
		service.delete(id);
	}
}
