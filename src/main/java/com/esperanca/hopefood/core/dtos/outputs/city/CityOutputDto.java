package com.esperanca.hopefood.core.dtos.outputs.city;

import com.esperanca.hopefood.core.dtos.outputs.state.StateSummaryOutputDto;
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
	private StateSummaryOutputDto state;
}
