package com.esperanca.hopefood.core.dtos.kitchens;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class KitchenOutputDto {
	private Long id;
	private String name;
	private String description;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
