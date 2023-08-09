package com.esperanca.hopefood.core.dtos.outputs.kitchen;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class KitchenCompleteOutputDto {
	private Long id;
	private String name;
	private String description;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
