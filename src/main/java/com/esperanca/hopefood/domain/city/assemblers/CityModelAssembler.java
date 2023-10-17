package com.esperanca.hopefood.domain.city.assemblers;

import com.esperanca.hopefood.domain.city.dtos.outputs.CityModel;
import com.esperanca.hopefood.domain.city.entities.City;
import com.esperanca.hopefood.core.assemblers.Assembler;
import com.esperanca.hopefood.core.beans.modelmapper.ModelMapperProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityModelAssembler extends ModelMapperProvider
		implements Assembler<City, CityModel> {

	@Override
	public CityModel toModel(City city) {
		return modelMapper.map(city, CityModel.class);
	}

	@Override
	public List<CityModel> toCollectionModel(List<City> cities) {
		return cities.stream()
				.map(this::toModel)
				.toList();
	}
}
