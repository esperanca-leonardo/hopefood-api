package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.api.dtos.kitchens.KitchenInputDto;
import com.esperanca.hopefood.api.dtos.kitchens.KitchenOutputDto;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.services.KitchenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * A REST controller for managing kitchens.
 * This controller provides methods for finding, saving, updating, and
 * deleting kitchens.
 */
@RestController
@RequestMapping("/kitchens")
@AllArgsConstructor
public class KitchenController {

	/**
	 * The service for managing kitchens.
	 */
	private KitchenService kitchenService;

	@GetMapping
	@ResponseStatus(OK)
	public List<KitchenOutputDto> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public KitchenOutputDto findById(@PathVariable Long id)
			throws KitchenNotFoundException {
		return kitchenService.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public KitchenOutputDto save(@RequestBody KitchenInputDto kitchenInputDto) {
		return kitchenService.save(kitchenInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public KitchenOutputDto update(@RequestBody KitchenInputDto kitchenInputDto,
			@PathVariable Long id) throws KitchenNotFoundException {
		return kitchenService.update(kitchenInputDto, id);
	}

	/**
	 * Deletes a kitchen by its ID.
	 * This method performs the deletion of a kitchen from the system based on
	 * the provided ID.
	 *
	 * @param id the ID of the kitchen to be deleted
	 * @throws KitchenNotFoundException if no kitchen is found with the provided
	 * ID
	 * @throws KitchenInUseException if the kitchen is in use and cannot be
	 * deleted
	 *
	 * @see KitchenService#delete(Long)
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws KitchenNotFoundException,
			KitchenInUseException {
		kitchenService.delete(id);
	}
}
