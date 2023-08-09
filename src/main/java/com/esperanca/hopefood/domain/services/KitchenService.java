package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenCompleteOutputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenSummaryOutputDto;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import com.esperanca.hopefood.domain.services.converters.GenericConverter;
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

	private KitchenRepository kitchenRepository;

	private GenericConverter<Kitchen, KitchenCompleteOutputDto,
			KitchenInputDto, KitchenSummaryOutputDto> kitchenConverter;

	private Kitchen convertToEntity(KitchenInputDto kitchenInputDto) {
		return kitchenConverter.convertToEntity(kitchenInputDto, Kitchen.class);
	}

	private KitchenSummaryOutputDto convertToSummaryDto(Kitchen kitchen) {
		return kitchenConverter.convertToSummaryDto(kitchen, KitchenSummaryOutputDto.class);
	}
	
	private KitchenCompleteOutputDto convertToCompleteDto(Kitchen kitchen) {
		return kitchenConverter.convertToCompleteDto(kitchen, KitchenCompleteOutputDto.class);
	}

	@Transactional(readOnly = true)
	public List<KitchenSummaryOutputDto> findAll() {
		return kitchenRepository.findAll()
				.stream()
				.map(this::convertToSummaryDto)
				.toList();
	}

	@Transactional(readOnly = true)
	public Kitchen findKitchenById(Long id) throws KitchenNotFoundException {
		return kitchenRepository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public KitchenCompleteOutputDto findById(Long id) throws KitchenNotFoundException {
		return kitchenRepository.findById(id)
				.map(this::convertToCompleteDto)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}

	@Transactional
	public KitchenCompleteOutputDto save(KitchenInputDto kitchenInputDto) {
		Kitchen kitchen = convertToEntity(kitchenInputDto);
		kitchen = kitchenRepository.save(kitchen);

		return convertToCompleteDto(kitchen);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public KitchenCompleteOutputDto update(KitchenInputDto kitchenInputDto, Long id)
			throws KitchenNotFoundException {

		Kitchen kitchen = findKitchenById(id);
		copyProperties(kitchenInputDto, kitchen);
		kitchen = kitchenRepository.save(kitchen);

		return convertToCompleteDto(kitchen);
	}

	public void delete(Long id) throws KitchenNotFoundException, KitchenInUseException {
		try {
			kitchenRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException exception) {
			throw new KitchenNotFoundException(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new KitchenInUseException(id);
		}
	}
}
