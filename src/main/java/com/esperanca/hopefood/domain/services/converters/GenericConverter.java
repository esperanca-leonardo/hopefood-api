package com.esperanca.hopefood.domain.services.converters;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public final class GenericConverter<Entity, CompleteDto, InputDto, SummaryDto> implements
		CompleteConverter<Entity, CompleteDto>, EntityConverter<InputDto, Entity>,
		SummaryConverter<Entity, SummaryDto> {

	private ModelMapper modelMapper;

	@Override
	public CompleteDto convertToCompleteDto(Entity sourceEntity,
			Class<CompleteDto> targetCompleteDtoClass) {
		return modelMapper.map(sourceEntity, targetCompleteDtoClass);
	}

	@Override
	public Entity convertToEntity(InputDto sourceInputDto,
			Class<Entity> targetEntityClass) {
		return modelMapper.map(sourceInputDto, targetEntityClass);
	}

	@Override
	public SummaryDto convertToSummaryDto(Entity sourceEntity,
			Class<SummaryDto> targetSummaryDtoClass) {
		return modelMapper.map(sourceEntity, targetSummaryDtoClass);
	}
}
