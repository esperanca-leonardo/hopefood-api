package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class for managing kitchens.
 * This class provides methods for finding, saving, updating, and deleting
 * kitchens.
 */
@Service
@AllArgsConstructor
public class KitchenService {

	/**
	 * The repository for kitchens.
	 */
	private KitchenRepository kitchenRepository;

	/**
	 * Finds all kitchens.
	 * This method finds all kitchens in the database.
	 * The method first calls the `findAll()` method of the `kitchenRepository`
	 * to find all kitchens.
	 * Then, the method returns the list of kitchens.
	 *
	 * @return the list of kitchens
	 *
	 * @see KitchenRepository#findAll
	 */
	public List<Kitchen> findAll() {
		return this.kitchenRepository.findAll();
	}

	/**
	 *  Finds a kitchen by its ID.
	 *  This method finds the kitchen with the specified ID in the database.
	 *  The id parameter is the ID of the kitchen to be found.
	 *  The method first calls the findById() method of the kitchenRepository
	 *  to find the kitchen with the specified ID.
	 *  If the kitchen is found, the method returns the kitchen.
	 *  If the kitchen is not found, the method throws a KitchenNotFoundException
	 *  exception.
	 *
	 *  @param id the ID of the kitchen to be found
	 *  @return the kitchen with the specified ID
	 *  @throws KitchenNotFoundException if the kitchen with the specified ID
	 *  is not found
	 *
	 *  @see KitchenRepository#findById
	 */
	public Kitchen findById(Long id) throws KitchenNotFoundException {
		return this.kitchenRepository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	/**
	 * Saves a kitchen.
	 * This method saves the kitchen to the database.
	 * The kitchen parameter is the kitchen to be saved.
	 * The method first calls the save() method of the kitchenRepository to save
	 * the kitchen to the database.
	 * Then, the method returns the saved kitchen.
	 *
	 * @param kitchen the kitchen to be saved
	 * @return the saved kitchen
	 *
	 * @see KitchenRepository#save
	 */
	public Kitchen save(Kitchen kitchen) {
		return this.kitchenRepository.save(kitchen);
	}

	/**
	 * Updates a kitchen by its ID.
	 * This method updates the kitchen with the specified ID with the data
	 * provided in the `kitchen` parameter.
	 * The `kitchen` parameter contains the new values for the kitchen's
	 * properties.
	 * The `id` parameter is the ID of the kitchen to be updated.
	 * The method first retrieves the kitchen with the specified ID from the
	 * database using the `findById()` method of the `kitchenRepository`.
	 * Then, the method copies the properties of the `kitchen` parameter to
	 * the kitchen retrieved from the database, ignoring the `id` property.
	 * Finally, the method saves the updated kitchen to the database using
	 * the `save()` method of the `kitchenRepository`.
	 *
	 * @param kitchen the kitchen with the new values
	 * @param id the ID of the kitchen to be updated
	 *
	 * @return the updated kitchen
	 *
	 * @throws KitchenNotFoundException if the kitchen with the specified ID is
	 * not found
	 *
	 * @see KitchenService#findById
	 * @see KitchenRepository#save
	 */
	public Kitchen update(Kitchen kitchen, Long id)
			throws KitchenNotFoundException {

		var kitchenFromDb = this.findById(id);
		BeanUtils.copyProperties(kitchen, kitchenFromDb, "id");

		return this.kitchenRepository.save(kitchenFromDb);
	}

	/**
	 * Deletes a kitchen by its ID.
	 * This method performs the deletion of a kitchen from the system based on
	 * the provided ID.
	 * It first attempts to delete the kitchen with the provided ID using the
	 * `deleteById` method of the `kitchenRepository`.
	 * If the deletion is successful, the method completes normally.
	 * If no kitchen is found with the provided ID, it throws a
	 * `KitchenNotFoundException` exception indicating that the kitchen was not
	 * found.
	 * If the kitchen is in use and cannot be deleted due to integrity
	 * constraints, it throws a `KitchenInUseException` exception indicating
	 * that the kitchen is in use and cannot be deleted.
	 *
	 * @param id the ID of the kitchen to be deleted
	 * @throws KitchenNotFoundException if no kitchen is found with the provided
	 * ID
	 * @throws KitchenInUseException if the kitchen is in use and cannot be
	 * deleted
	 *
	 * @see KitchenRepository#deleteById
	 */
	public void delete(Long id) throws KitchenNotFoundException,
			KitchenInUseException {

		try {
			this.kitchenRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException exception) {
			throw new KitchenNotFoundException(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new KitchenInUseException(id);
		}
	}
}
