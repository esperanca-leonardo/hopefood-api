package com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics;

public interface EntityConverter<InputDto, Entity> {

	Entity toEntity(InputDto inputDto);
}
