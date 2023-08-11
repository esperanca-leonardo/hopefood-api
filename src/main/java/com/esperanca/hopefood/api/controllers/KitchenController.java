package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenSummaryDto;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	@ResponseStatus(OK)
	public List<KitchenSummaryDto> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public KitchenCompleteDto findById(@PathVariable Long id) throws KitchenNotFoundException {
		return kitchenService.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public KitchenCompleteDto save(@RequestBody @Valid KitchenInputDto kitchenInputDto) {
		return kitchenService.save(kitchenInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public KitchenCompleteDto update(@RequestBody @Valid KitchenInputDto
			kitchenInputDto, @PathVariable Long id) throws KitchenNotFoundException {
		return kitchenService.update(kitchenInputDto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws
			KitchenNotFoundException, KitchenInUseException {
		kitchenService.delete(id);
	}
}
