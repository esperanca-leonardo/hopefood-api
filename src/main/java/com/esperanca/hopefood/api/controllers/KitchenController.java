package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.kitchens.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.kitchens.KitchenOutputDto;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.services.KitchenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/kitchens")
@AllArgsConstructor
public class KitchenController {

	private KitchenService kitchenService;

	@GetMapping
	@ResponseStatus(OK)
	public List<KitchenOutputDto> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public KitchenOutputDto findById(@PathVariable Long id) throws KitchenNotFoundException {
		return kitchenService.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public KitchenOutputDto save(@RequestBody @Valid KitchenInputDto kitchenInputDto) {
		return kitchenService.save(kitchenInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public KitchenOutputDto update(@RequestBody @Valid KitchenInputDto kitchenInputDto,
			@PathVariable Long id) throws KitchenNotFoundException {
		return kitchenService.update(kitchenInputDto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws KitchenNotFoundException,
			KitchenInUseException {
		kitchenService.delete(id);
	}
}
