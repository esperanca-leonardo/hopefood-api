package com.esperanca.hopefood.domain.services.utils.converters.impl;

import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenSummaryDto;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.kitchen.KitchenConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenConverterImpl implements KitchenConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Kitchen toEntity(KitchenInputDto kitchenInputDto) {
		return modelMapper.map(kitchenInputDto, Kitchen.class);
	}

	@Override
	public KitchenSummaryDto toSummary(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenSummaryDto.class);
	}

	@Override
	public KitchenCompleteDto toCompleteDto(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenCompleteDto.class);
	}
}
