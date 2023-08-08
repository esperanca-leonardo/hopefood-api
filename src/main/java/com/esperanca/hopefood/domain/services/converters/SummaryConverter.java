package com.esperanca.hopefood.domain.services.converters;

public sealed interface SummaryConverter<Entity, SummaryDto> permits GenericConverter {

	SummaryDto convertToSummaryDto(Entity sourceEntity,
		Class<SummaryDto> targetSummaryDtoClass
	);
}
