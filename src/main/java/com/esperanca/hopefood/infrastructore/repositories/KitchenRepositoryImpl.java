package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the KitchenRepository interface.
 *
 * This class provides the implementation for CRUD operations related to
 * Kitchen entities.
 * It interacts with the database using the EntityManager to perform data
 * access operations.
 */
@Component
public class KitchenRepositoryImpl implements KitchenRepository {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Retrieve all Kitchens.
   *
   * This method retrieves all Kitchens from the system.
   * It creates a query using the EntityManager to fetch all Kitchen objects.
   * It returns a List containing all the retrieved Kitchen objects.
   *
   * @return a List of all Kitchen objects
   */
  @Override
  public List<Kitchen> findAll() {
    return this.entityManager.createQuery("from Kitchen", Kitchen.class)
        .getResultList();
  }

  /**
   * Find a Kitchen by ID.
   *
   * This method retrieves a Kitchen from the system based on the provided ID.
   * It uses the EntityManager to find the Kitchen by its ID.
   * If a Kitchen with the provided ID is found, it returns the corresponding
   * Kitchen object.
   * If no Kitchen is found with the provided ID, it returns null.
   *
   * @param id the ID of the Kitchen to retrieve
   * @return the Kitchen object found by ID, or null if not found
   */
  @Override
  public Kitchen findById(Long id) {
    return this.entityManager.find(Kitchen.class, id);
  }

  /**
   * Saves a Kitchen.
   *
   * This method handles the saving of a Kitchen in the system.
   * It merges the provided Kitchen object using the EntityManager.
   * If the saving is successful, it returns the saved Kitchen object.
   *
   * @param kitchen the Kitchen object to be saved
   * @return the saved Kitchen object
   */
  @Override
  @Transactional
  public Kitchen save(Kitchen kitchen) {
    return this.entityManager.merge(kitchen);
  }

  /**
   * Removes a Kitchen by ID.
   *
   * This method removes a Kitchen from the system based on the provided ID.
   * It first checks if the Kitchen with the provided ID exists.
   * If the Kitchen does not exist, it throws an EmptyResultDataAccessException.
   * If the Kitchen exists, it removes the Kitchen from the system.
   *
   * @param id the ID of the Kitchen to be removed
   * @throws EmptyResultDataAccessException if the Kitchen with the provided ID
   * does not exist
   */
  @Override
  @Transactional
  public void remove(Long id) {
    var kitchen = this.findById(id);

    if (Objects.isNull(kitchen)) {
     throw new EmptyResultDataAccessException(1);
    }
    this.entityManager.remove(kitchen);
  }
}
