package com.esperanca.hopefood.domain.services.converters;

public sealed interface CompleteConverter<Entity, CompleteDto> permits GenericConverter {

	CompleteDto convertToCompleteDto(Entity sourceEntity,
		Class<CompleteDto> targetCompleteDtoClass
	);
}
