package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.kitchens.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.kitchens.KitchenOutputDto;
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

@Service
@AllArgsConstructor
public class KitchenService {

	private ModelMapper modelMapper;

	private KitchenRepository kitchenRepository;

	private KitchenOutputDto convertToOutputDto(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenOutputDto.class);
	}

	public List<KitchenOutputDto> findAll() {
		return kitchenRepository.findAll()
				.stream()
				.map(this::convertToOutputDto)
				.toList();
	}

	public KitchenOutputDto findById(Long id) throws KitchenNotFoundException {
		return kitchenRepository.findById(id)
				.map(this::convertToOutputDto)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	public KitchenOutputDto save(KitchenInputDto kitchenInputDto) {
		Kitchen kitchen = modelMapper.map(kitchenInputDto, Kitchen.class);
		kitchen = kitchenRepository.save(kitchen);

		return convertToOutputDto(kitchen);
	}

	public KitchenOutputDto update(KitchenInputDto kitchenInputDto, Long id)
			throws KitchenNotFoundException {

		Kitchen kitchen = kitchenRepository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id));

		modelMapper.map(kitchenInputDto, kitchen);
		kitchen = kitchenRepository.save(kitchen);

		return convertToOutputDto(kitchen);
	}

	public void delete(Long id) throws KitchenNotFoundException, KitchenInUseException {
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
