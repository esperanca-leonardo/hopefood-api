package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.inputs.state.StateInputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateCompleteOutputDto;
import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryOutputDto;
import com.esperanca.hopefood.domain.exceptions.state.StateInUseException;
import com.esperanca.hopefood.domain.exceptions.state.StateNotFoundException;
import com.esperanca.hopefood.domain.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/states")
@AllArgsConstructor
public class StateController {

	private StateService stateService;

	@GetMapping
	@ResponseStatus(OK)
	public List<StateSummaryOutputDto> findAll() {
		return stateService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public StateCompleteOutputDto findById(@PathVariable Long id) throws StateNotFoundException {
		return stateService.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public StateCompleteOutputDto save(@RequestBody @Valid StateInputDto stateInputDto) {
		return stateService.save(stateInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public StateCompleteOutputDto update(@RequestBody @Valid StateInputDto stateInputDto,
			@PathVariable Long id) throws StateNotFoundException {
		return stateService.update(stateInputDto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws StateNotFoundException, StateInUseException {
		stateService.delete(id);
	}
}
