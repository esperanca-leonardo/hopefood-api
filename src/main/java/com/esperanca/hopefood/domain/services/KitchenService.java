package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;
import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

	private KitchenRepository kitchenRepository;

	public KitchenService(KitchenRepository kitchenRepository) {
		this.kitchenRepository = kitchenRepository;
	}

	public List<Kitchen> findAll() {
		return this.kitchenRepository.findAll();
	}

	public Kitchen findById(Long id) {
		return this.kitchenRepository.findById(id);
	}

	public Kitchen save(Kitchen kitchen) {
		return this.kitchenRepository.save(kitchen);
	}

	public void delete(Long id) throws EntityInUseException,
			EntityNotFoundException {

		try {
			this.kitchenRepository.remove(id);
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
