package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;
import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Controller class for handling Kitchen-related HTTP requests.
 * Exposes various endpoints to perform CRUD operations on Kitchen objects.
 *
 * @RestController Indicates that this class serves as a RESTful web service
 * controller.
 * @RequestMapping Specifies the base URL mapping for all endpoints in this
 * controller.
 */
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	private KitchenService kitchenService;

	public KitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	/**
	 * Retrieves all Kitchens.
	 *
	 * This method retrieves all Kitchens from the system.
	 * It returns a List of Kitchen objects with HTTP status 200 (OK).
	 *
	 * @return a List of all Kitchen objects
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Kitchen> findAll() {
		return this.kitchenService.findAll();
	}

	/**
	 * Retrieves a Kitchen by ID.
	 *
	 * This method retrieves a Kitchen from the system based on the provided ID.
	 * If the Kitchen with the provided ID is found, it returns a ResponseEntity
	 * with HTTP status 200 (OK)
	 * and the retrieved Kitchen object in the response body.
	 * If the Kitchen with the provided ID is not found, it returns a
	 * ResponseEntity with HTTP status 404 (NOT_FOUND) and an empty response
	 * body.
	 *
	 * @param id the ID of the Kitchen to retrieve
	 * @return a ResponseEntity representing the HTTP response, with status and
	 * body
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
		var kitchen = this.kitchenService.findById(id);
		var httpStatus = HttpStatus.OK;

		if (Objects.isNull(kitchen)) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return ResponseEntity.status(httpStatus).body(kitchen);
	}

	/**
	 * Saves a new Kitchen.
	 *
	 * This method handles the creation of a new Kitchen in the system.
	 * It receives a Kitchen object in the request body and returns the saved
	 * Kitchen object with HTTP status 201 (CREATED).
	 *
	 * @param kitchen the Kitchen object to be saved
	 * @return the saved Kitchen object
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return this.kitchenService.save(kitchen);
	}

	/**
	 * Updates a Kitchen by ID.
	 *
	 * This method handles the update of a Kitchen in the system based on the
	 * provided ID.
	 * If the Kitchen with the provided ID is found and successfully updated,
	 * it returns a ResponseEntity with HTTP status 200 (OK)
	 * and the updated Kitchen object in the response body.
	 * If the Kitchen with the provided ID is not found, it returns a
	 * ResponseEntity with HTTP status 404 (NOT_FOUND) and an empty response body.
	 *
	 * @param kitchen the Kitchen object containing the updated information
	 * @param id the ID of the Kitchen to be updated
	 * @return a ResponseEntity representing the HTTP response, with status and body
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@RequestBody Kitchen kitchen,
			@PathVariable Long id) {

		var kitchenFromDb = this.kitchenService.findById(id);
		var httpStatus = HttpStatus.OK;

		if (Objects.nonNull(kitchenFromDb)) {
			BeanUtils.copyProperties(kitchen, kitchenFromDb, "id");
			this.kitchenService.save(kitchenFromDb);
		}
		else {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return ResponseEntity.status(httpStatus).body(kitchenFromDb);
	}

	/**
	 * Deletes a Kitchen by ID.
	 *
	 * This method handles the deletion of a Kitchen from the system based on
	 * the provided ID.
	 * If the deletion is successful, it returns a ResponseEntity with HTTP status
	 * 204 (NO_CONTENT).
	 * If the Kitchen is not found, it returns a ResponseEntity with HTTP status
	 * 404 (NOT_FOUND) and an error message.
	 * If the deletion fails due to the Kitchen being currently in use, it
	 * returns a ResponseEntity with HTTP status 409 (CONFLICT)
	 * and an error message indicating the reason for the conflict.
	 *
	 * @param id the ID of the Kitchen to be deleted
	 * @return a ResponseEntity representing the HTTP response, with the
	 * appropriate status and error message if applicable
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var httpStatus = HttpStatus.NO_CONTENT;
		var message = "";

		try {
			this.kitchenService.delete(id);
		}
		catch (EntityNotFoundException exception) {
			message = exception.getMessage();
			httpStatus = HttpStatus.NOT_FOUND;
		}
		catch (EntityInUseException exception) {
			message = exception.getMessage();
			httpStatus = HttpStatus.CONFLICT;
		}
		return ResponseEntity.status(httpStatus).body(message);
	}
}