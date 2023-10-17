package com.esperanca.hopefood.domain.state.controllers;

import com.esperanca.hopefood.domain.state.dtos.inputs.StateInput;
import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;
import com.esperanca.hopefood.domain.state.exceptions.StateInUseException;
import com.esperanca.hopefood.domain.state.services.crud.StateCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/states")
public class StateCrudControllerImpl implements StateCrudController {

	@Autowired
	private StateCrudService service;

	@Override
	@GetMapping
	public List<StateOutput> findAll() {
		return service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public StateOutput findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@Override
	@PostMapping
	@ResponseStatus(CREATED)
	public StateOutput save(@RequestBody @Valid StateInput stateInput) {
		return service.save(stateInput);
	}

	@Override
	@PutMapping("/{id}")
	public StateOutput update(@PathVariable Long id,
			@RequestBody @Valid StateInput stateInput) {
		return service.update(id, stateInput);
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws StateInUseException {
		service.deleteById(id);
	}
}
