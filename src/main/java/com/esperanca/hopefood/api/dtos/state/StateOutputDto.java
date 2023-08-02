package com.esperanca.hopefood.api.dtos.state;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class StateOutputDto {
	private Long id;
	private String name;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
