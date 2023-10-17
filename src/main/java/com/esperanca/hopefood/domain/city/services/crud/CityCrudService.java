package com.esperanca.hopefood.domain.city.services.crud;

import com.esperanca.hopefood.core.interfaces.crud.Crud;
import com.esperanca.hopefood.domain.city.dtos.inputs.CityInput;
import com.esperanca.hopefood.domain.city.dtos.outputs.CityModel;
import com.esperanca.hopefood.domain.city.exceptions.CityInUseException;

import java.util.List;

public interface CityCrudService
		extends Crud<Long, CityInput, CityModel> {

	@Override
	List<CityModel> findAll();

	@Override
	CityModel findById(Long id);

	@Override
	CityModel save(CityInput cityInput);

	@Override
	CityModel update(Long id, CityInput cityInput);

	@Override
	void deleteById(Long id) throws CityInUseException;
}
