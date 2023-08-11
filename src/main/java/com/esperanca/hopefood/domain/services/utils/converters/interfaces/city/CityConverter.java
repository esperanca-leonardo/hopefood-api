package com.esperanca.hopefood.domain.services.utils.converters.interfaces.city;

import com.esperanca.hopefood.core.dtos.inputs.city.CityInputDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CityCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CitySummaryDto;
import com.esperanca.hopefood.domain.models.City;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics.Converter;

public interface CityConverter extends Converter<City, CityInputDto,
		CitySummaryDto, CityCompleteDto> {
}
