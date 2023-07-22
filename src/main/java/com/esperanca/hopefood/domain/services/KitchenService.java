package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.api.dtos.kitchens.KitchenInputDto;
import com.esperanca.hopefood.api.dtos.kitchens.KitchenOutputDto;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A service class for managing kitchens.
 * This class provides methods for finding, saving, updating, and deleting
 * kitchens.
 */
@Service
@AllArgsConstructor
public class KitchenService {

	private ModelMapper modelMapper;

	/**
	 * The repository for kitchens.
	 */
	private KitchenRepository kitchenRepository;

	private KitchenOutputDto convertToOutputDto(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenOutputDto.class);
	}

	public List<KitchenOutputDto> findAll() {
		return this.kitchenRepository.findAll()
				.stream()
				.map(this::convertToOutputDto)
				.collect(Collectors.toList());
	}

	public KitchenOutputDto findById(Long id) throws KitchenNotFoundException {
		return this.kitchenRepository.findById(id)
				.map(this::convertToOutputDto)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	public KitchenOutputDto save(KitchenInputDto kitchenInputDto) {
		Kitchen kitchen = modelMapper.map(kitchenInputDto, Kitchen.class);
		kitchen = this.kitchenRepository.save(kitchen);

		return modelMapper.map(kitchen, KitchenOutputDto.class);
	}

	public KitchenOutputDto update(KitchenInputDto kitchenInputDto, Long id)
			throws KitchenNotFoundException {

		var kitchen = kitchenRepository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id));

		modelMapper.map(kitchenInputDto, kitchen);
		kitchen = kitchenRepository.save(kitchen);

		return modelMapper.map(kitchen, KitchenOutputDto.class);
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
