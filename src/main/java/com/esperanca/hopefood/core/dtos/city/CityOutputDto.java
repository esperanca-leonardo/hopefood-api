package com.esperanca.hopefood.core.dtos.city;

import com.esperanca.hopefood.core.dtos.state.StateOutputDto;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CityOutputDto {
	private Long id;
	private String name;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
	private StateOutputDto state;
}
