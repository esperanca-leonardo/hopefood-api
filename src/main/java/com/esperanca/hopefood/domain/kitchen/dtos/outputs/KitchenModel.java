package com.esperanca.hopefood.domain.kitchen.dtos.outputs;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class KitchenModel {
	private Long id;
	private String name;
	private String description;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
