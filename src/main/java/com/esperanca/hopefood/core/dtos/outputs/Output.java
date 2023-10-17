package com.esperanca.hopefood.core.dtos.outputs;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public abstract class Output {
	protected Long id;
	protected OffsetDateTime createdAt;
	protected OffsetDateTime updatedAt;
}
