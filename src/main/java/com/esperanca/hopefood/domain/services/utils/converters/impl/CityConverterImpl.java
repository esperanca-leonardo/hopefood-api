package com.esperanca.hopefood.domain.services.utils.converters.impl;

import com.esperanca.hopefood.core.dtos.inputs.city.CityInputDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CityCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CitySummaryDto;
import com.esperanca.hopefood.domain.models.City;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.city.CityConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityConverterImpl implements CityConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public City toEntity(CityInputDto inputDto) {
		return modelMapper.map(inputDto, City.class);
	}

	@Override
	public CitySummaryDto toSummary(City entity) {
		return modelMapper.map(entity, CitySummaryDto.class);
	}

	@Override
	public CityCompleteDto toCompleteDto(City entity) {
		return modelMapper.map(entity, CityCompleteDto.class);
	}
}
