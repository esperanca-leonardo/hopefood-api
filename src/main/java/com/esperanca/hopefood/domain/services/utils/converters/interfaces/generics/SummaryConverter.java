package com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics;

public interface SummaryConverter<Entity, SummaryDto> {

	SummaryDto toSummary(Entity entity);
}
