package com.esperanca.hopefood.domain.services.utils.propertycopiers.impl;

import com.esperanca.hopefood.core.dtos.inputs.city.CityInputDto;
import com.esperanca.hopefood.domain.models.City;
import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.services.utils.propertycopiers.interfaces.CityPropertyCopier;
import com.esperanca.hopefood.domain.services.shared.AbstractMapperProvider;
import org.springframework.stereotype.Component;

@Component
public class CityPropertyCopierImpl extends AbstractMapperProvider
		implements CityPropertyCopier {

	@Override
	public void copyProperties(CityInputDto cityInputDto, City city) {
		city.setState(new State());
		modelMapper.map(cityInputDto, city);
	}
}
