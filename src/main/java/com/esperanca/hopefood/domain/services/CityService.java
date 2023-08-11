package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.inputs.city.CityInputDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CityCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CitySummaryDto;
import com.esperanca.hopefood.domain.exceptions.city.CityInUseException;
import com.esperanca.hopefood.domain.exceptions.city.CityNotFoundException;
import com.esperanca.hopefood.domain.models.City;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.repositories.CityRepository;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.city.CityConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class CityService {

	private CityConverter converter;

	private CityRepository repository;

	private StateService stateService;

	private Long getStateId(CityInputDto cityInputDto) {
		return cityInputDto.getState().getId();
	}

	@Transactional(readOnly = true)
	public City findCityById(Long id) throws CityNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new CityNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public List<CitySummaryDto> findAll() {
		return repository.findAll()
				.stream()
				.map(city -> converter.toSummary(city))
				.toList();
	}

	@Transactional(readOnly = true)
	public CityCompleteDto findById(Long id) throws CityNotFoundException {
		return repository.findById(id)
				.map(city -> converter.toCompleteDto(city))
				.orElseThrow(() -> new CityNotFoundException(id));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public CityCompleteDto save(CityInputDto cityInputDto) {
		City city = converter.toEntity(cityInputDto);
		final Long stateId = getStateId(cityInputDto);
		final State state = stateService.findStateById(stateId);

		city.setState(state);
		city = repository.save(city);

		return converter.toCompleteDto(city);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public CityCompleteDto update(CityInputDto cityInputDto, Long id)
			throws CityNotFoundException {

		City city = findCityById(id);
		final Long stateId = getStateId(cityInputDto);
		final State state = stateService.findStateById(stateId);

		copyProperties(cityInputDto, city ,"state");

		city.setState(state);
		city = repository.save(city);

		return converter.toCompleteDto(city);
	}

	@Transactional
	public void delete(Long id) throws CityNotFoundException, CityInUseException {
		try {
			repository.deleteById(id);
			repository.flush();
		}
		catch (EmptyResultDataAccessException exception) {
			throw new CityNotFoundException(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new CityInUseException(id);
		}
	}
}