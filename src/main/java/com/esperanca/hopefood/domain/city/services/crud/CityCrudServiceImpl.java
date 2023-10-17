package com.esperanca.hopefood.domain.city.services.crud;

import com.esperanca.hopefood.domain.city.dtos.inputs.CityInput;
import com.esperanca.hopefood.domain.city.dtos.outputs.CityModel;
import com.esperanca.hopefood.domain.city.entities.City;
import com.esperanca.hopefood.domain.city.assemblers.CityModelAssembler;
import com.esperanca.hopefood.domain.city.exceptions.CityInUseException;
import com.esperanca.hopefood.domain.city.repositories.CityRepository;
import com.esperanca.hopefood.domain.city.services.findbyid.CityFindByIdService;
import com.esperanca.hopefood.domain.state.entities.State;
import com.esperanca.hopefood.domain.state.services.findbyid.StateFindByIdService;
import com.esperanca.hopefood.domain.city.assemblers.CityInputDisassembler;
import com.esperanca.hopefood.core.utils.propertycopier.PropertyCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityCrudServiceImpl implements CityCrudService {

	@Autowired
	private PropertyCopier copier;

	@Autowired
	private CityRepository repository;

	@Autowired
	private CityFindByIdService service;

	@Autowired
	private CityModelAssembler assembler;

	@Autowired
	private StateFindByIdService stateService;

	@Autowired
	private CityInputDisassembler disassembler;

	@Override
	@Transactional(readOnly = true)
	public List<CityModel> findAll() {
		final List<City> cities = repository.findAll();
		return assembler.toCollectionModel(cities);
	}

	@Override
	@Transactional(readOnly = true)
	public CityModel findById(Long id) {
		return repository.findById(id)
				.map(city -> assembler.toModel(city))
				.orElseGet(CityModel::new);
	}

	@Override
	@Transactional
	public CityModel save(CityInput cityInput) {
		final State state = stateService.findById(cityInput);
		City city = disassembler.toEntity(cityInput);

		city.updateState(state);
		city = repository.save(city);

		return assembler.toModel(city);
	}

	@Override
	@Transactional
	public CityModel update(Long id, CityInput cityInput) {
		final State state = stateService.findById(cityInput);
		City city = service.findById(id);

		city.updateState(state);
		copier.copyProperties(cityInput, city);
		city = repository.save(city);

		return assembler.toModel(city);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws CityInUseException {
		try {
			repository.deleteById(id);
			repository.flush();
		}
		catch (DataIntegrityViolationException exception) {
			throw new CityInUseException(id);
		}
	}
}