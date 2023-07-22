package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
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

	/**
	 * Finds all kitchens.
	 * This method finds all kitchens in the database.
	 *
	 * @return the list of kitchens
	 *
	 * @see KitchenService#findAll()
	 */
	@GetMapping
	@ResponseStatus(OK)
	public List<Kitchen> findAll() {
		return kitchenService.findAll();
	}

	/**
	 * Finds a kitchen by its ID.
	 * This method finds the kitchen with the specified ID in the database.
	 *
	 * @param id the ID of the kitchen to be found
	 * @return the kitchen with the specified ID
	 * @throws KitchenNotFoundException if the kitchen with the specified ID
	 * is not found
	 *
	 * @see KitchenService#findById(Long)
	 */
	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public Kitchen findById(@PathVariable Long id)
			throws KitchenNotFoundException {
		return kitchenService.findById(id);
	}

	/**
	 * Saves a kitchen.
	 * This method saves the kitchen to the database.
	 *
	 * @param kitchen the kitchen to be saved
	 * @return the saved kitchen
	 *
	 * @see KitchenService#save(Kitchen)
	 */
	@PostMapping
	@ResponseStatus(CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	/**
	 * Updates a kitchen by its ID.
	 * This method updates the kitchen with the specified ID with the data
	 * provided in the `kitchen` parameter.
	 *
	 * @param kitchen the kitchen with the new values
	 * @param id the ID of the kitchen to be updated
	 * @return the updated kitchen
	 * @throws KitchenNotFoundException if the kitchen with the specified ID
	 * is not found
	 *
	 * @see KitchenService#update(Kitchen, Long)
	 */
	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public Kitchen update(@RequestBody Kitchen kitchen, @PathVariable Long id)
			throws KitchenNotFoundException {
		return kitchenService.update(kitchen, id);
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
