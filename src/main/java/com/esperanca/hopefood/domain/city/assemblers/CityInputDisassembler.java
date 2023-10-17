package com.esperanca.hopefood.domain.city.assemblers;

import com.esperanca.hopefood.domain.city.dtos.inputs.CityInput;
import com.esperanca.hopefood.domain.city.entities.City;
import com.esperanca.hopefood.core.assemblers.Disassembler;
import com.esperanca.hopefood.core.beans.modelmapper.ModelMapperProvider;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler extends ModelMapperProvider
		implements Disassembler<CityInput, City> {

	@Override
	public City toEntity(CityInput cityInput) {
		return modelMapper.map(cityInput, City.class);
	}
}
