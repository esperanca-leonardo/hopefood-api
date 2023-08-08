package com.esperanca.hopefood.domain.services.converters;

public sealed interface EntityConverter<InputDto, Entity> permits GenericConverter {

	Entity convertToEntity(InputDto sourceInputDto,
		Class<Entity> targetEntityClass
	);
}
