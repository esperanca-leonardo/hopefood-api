package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;
import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Kitchen-related operations.
 *
 * This class provides the business logic for handling Kitchen entities.
 * It delegates the data access operations to the underlying kitchenRepository
 * and handles any exceptions that may occur.
 */
@Service
@AllArgsConstructor
public class KitchenService {

	private KitchenRepository kitchenRepository;

	/**
	 * Retrieve all Kitchens.
	 *
	 * This method retrieves all Kitchens from the system.
	 * It delegates the retrieval operation to the underlying kitchenRepository,
	 * which is responsible for fetching all the Kitchen objects.
	 * It returns a List containing all the retrieved Kitchen objects.
	 *
	 * @return a List of all Kitchen objects
	 */
	public List<Kitchen> findAll() {
		return this.kitchenRepository.findAll();
	}

	/**
	 * Find a Kitchen by ID.
	 *
	 * This method retrieves a Kitchen from the system based on the provided ID.
	 * It delegates the retrieval operation to the underlying kitchenRepository,
	 * which is responsible for fetching the Kitchen object with the
	 * corresponding ID.
	 * If a Kitchen with the provided ID is found, it returns the corresponding
	 * Kitchen object.
	 * If no Kitchen is found with the provided ID, it returns null.
	 *
	 * @param id the ID of the Kitchen to retrieve
	 * @return the Kitchen object found by ID, or null if not found
	 */
	public Optional<Kitchen> findById(Long id) {
		return this.kitchenRepository.findById(id);
	}

	/**
	 * Saves a Kitchen.
	 *
	 * This method handles the saving of a Kitchen in the system.
	 * It delegates the saving operation to the underlying kitchenRepository,
	 * which is responsible for persisting the Kitchen object.
	 * If the saving is successful, it returns the saved Kitchen object.
	 *
	 * @param kitchen the Kitchen object to be saved
	 * @return the saved Kitchen object
	 */
	public Kitchen save(Kitchen kitchen) {
		return this.kitchenRepository.save(kitchen);
	}

	/**
	 * Deletes a Kitchen by ID.
	 *
	 * This method handles the deletion of a Kitchen from the system based on the
	 * provided ID.
	 *
	 * @param id the ID of the Kitchen to be deleted
	 * @throws EntityInUseException if the Kitchen is currently in use and cannot
	 * be deleted
	 * @throws EntityNotFoundException if the Kitchen with the provided ID is not
	 * found
	 */
	public void delete(Long id) throws EntityInUseException,
			EntityNotFoundException {

		try {
			this.kitchenRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException exception) {
			throw new EntityNotFoundException(
					String.format("Entity not found with ID %d", id)
			);
		}
		catch (DataIntegrityViolationException exception) {
			throw new EntityInUseException(
					String.format("Entity in use with ID %d", id)
			);
		}
	}
}
