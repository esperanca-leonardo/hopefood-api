package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenSummaryDto;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import com.esperanca.hopefood.domain.services.utils.converters.impl.KitchenConverterImpl;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class KitchenService {

	private KitchenRepository repository;

	private KitchenConverterImpl converter;

	@Transactional(readOnly = true)
	public List<KitchenSummaryDto> findAll() {
		return repository.findAll()
				.stream()
				.map(kitchen -> converter.toSummary(kitchen))
				.toList();
	}

	@Transactional(readOnly = true)
	public Kitchen findKitchenById(Long id) throws KitchenNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public KitchenCompleteDto findById(Long id) throws KitchenNotFoundException {
		return repository.findById(id)
				.map(kitchen -> converter.toCompleteDto(kitchen))
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	@Transactional
	public KitchenCompleteDto save(KitchenInputDto kitchenInputDto) {
		Kitchen kitchen = converter.toEntity(kitchenInputDto);
		kitchen = repository.save(kitchen);

		return converter.toCompleteDto(kitchen);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public KitchenCompleteDto update(KitchenInputDto kitchenInputDto, Long id)
			throws KitchenNotFoundException {

		Kitchen kitchen = findKitchenById(id);
		copyProperties(kitchenInputDto, kitchen);
		kitchen = repository.save(kitchen);

		return converter.toCompleteDto(kitchen);
	}

	@Transactional
	public void delete(Long id) throws KitchenNotFoundException, KitchenInUseException {
		try {
			repository.deleteById(id);
			repository.flush();
		}
		catch (EmptyResultDataAccessException exception) {
			throw new KitchenNotFoundException(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new KitchenInUseException(id);
		}
	}
}
