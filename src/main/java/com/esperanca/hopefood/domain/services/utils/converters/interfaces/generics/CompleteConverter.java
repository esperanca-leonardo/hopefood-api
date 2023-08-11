package com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics;

public interface CompleteConverter<Entity, CompleteDto> {

	CompleteDto toCompleteDto(Entity entity);
}
