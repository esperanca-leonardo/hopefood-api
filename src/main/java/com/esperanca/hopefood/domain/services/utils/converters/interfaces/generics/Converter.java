package com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics;

public interface Converter<Entity, InputDto, SummaryDto, CompleteDto>
		extends SummaryConverter<Entity, SummaryDto>,
		EntityConverter<InputDto, Entity>,
		CompleteConverter<Entity, CompleteDto> {
}
