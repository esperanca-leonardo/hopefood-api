package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;
import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.KitchenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
@AllArgsConstructor
public class KitchenController {

	private KitchenService kitchenService;

	/**
	 * Retrieves all Kitchens.
	 *
	 * This method retrieves all Kitchens from the system.
	 * It returns a List of Kitchen objects with HTTP status 200 (OK).
	 *
	 * @return a List of all Kitchen objects
	 */
	@GetMapping
	@ResponseStatus(OK)
	public List<Kitchen> findAll() {
		return this.kitchenService.findAll();
	}

	/**
	 * Find a Kitchen by ID.
	 *
	 * This method retrieves a Kitchen from the system based on the provided ID.
	 * It delegates the retrieval operation to the kitchenService's findById
	 * method.
	 * If a Kitchen with the provided ID is found, it sets the HTTP status to
	 * 200 (OK)
	 * and assigns the retrieved Kitchen object to KITCHEN_FROM_DB.
	 * If no Kitchen is found with the provided ID, it sets the HTTP status to
	 * 404 (NOT_FOUND).
	 * It returns a ResponseEntity object containing the HTTP status and the
	 * KITCHEN_FROM_DB object in the response body.
	 *
	 * @param id the ID of the Kitchen to retrieve
	 * @return a ResponseEntity representing the HTTP response, with status and
	 * body
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
		HttpStatus httpStatus = OK;
		Kitchen kitchen = null;

		try {
			kitchen = this.kitchenService.findById(id);
		}
		catch (EntityNotFoundException exception) {
			httpStatus = NOT_FOUND;
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
	@ResponseStatus(CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return this.kitchenService.save(kitchen);
	}

	/**
	 * Update a Kitchen by ID.
	 *
	 * This method updates a Kitchen in the system based on the provided ID.
	 * It retrieves the existing Kitchen from the kitchenService using the
	 * findById method.
	 * If a Kitchen with the provided ID is found, it copies the properties from
	 * the given
	 * Kitchen object to the existing Kitchen object retrieved from the database,
	 * excluding the ID.
	 * It saves the updated Kitchen object using the kitchenService's save
	 * method and assigns
	 * the saved Kitchen object to kitchenSaved.
	 * If no Kitchen is found with the provided ID, it sets the HTTP status to
	 * 404 (NOT_FOUND).
	 * It returns a ResponseEntity object containing the HTTP status and the
	 * kitchenSaved object
	 * in the response body.
	 *
	 * @param kitchen the Kitchen object containing the updated information
	 * @param id the ID of the Kitchen to be updated
	 * @return a ResponseEntity representing the HTTP response, with status
	 * and body
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@RequestBody Kitchen kitchen,
			@PathVariable Long id) {

		HttpStatus httpStatus = OK;
		Kitchen kitchenSaved = null;

		try {
			final var KITCHEN_FROM_DB = this.kitchenService.findById(id);

			BeanUtils.copyProperties(kitchen, KITCHEN_FROM_DB,
					"id"
			);
			kitchenSaved = this.kitchenService.save(KITCHEN_FROM_DB);
		}
		catch (EntityNotFoundException exception) {
			httpStatus = NOT_FOUND;
		}
		return ResponseEntity.status(httpStatus).body(kitchenSaved);
	}

	/**
	 * Delete a Kitchen by ID.
	 *
	 * This method handles the deletion of a Kitchen from the system based on
	 * the provided ID.
	 * It delegates the deletion operation to the kitchenService's delete method.
	 * If the Kitchen is successfully deleted, it sets the HTTP status to 204
	 * (NO_CONTENT)
	 * and returns an empty response body.
	 * If no Kitchen is found with the provided ID, it sets the HTTP status to
	 * 404 (NOT_FOUND)
	 * and assigns the error message from the EntityNotFoundException to the
	 * 'message' variable.
	 * If the Kitchen is currently in use and cannot be deleted, it sets the
	 * HTTP status to 409 (CONFLICT)
	 * and assigns the error message from the EntityInUseException to the
	 * 'message' variable.
	 * It returns a ResponseEntity object containing the HTTP status and the
	 * 'message' in the response body.
	 *
	 * @param id the ID of the Kitchen to be deleted
	 * @return a ResponseEntity representing the HTTP response, with status
	 * and body
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		HttpStatus httpStatus = NO_CONTENT;
		var message = "";

		try {
			this.kitchenService.delete(id);
		}
		catch (EntityNotFoundException exception) {
			message = exception.getMessage();
			httpStatus = NOT_FOUND;
		}
		catch (EntityInUseException exception) {
			message = exception.getMessage();
			httpStatus = CONFLICT;
		}
		return ResponseEntity.status(httpStatus).body(message);
	}
}