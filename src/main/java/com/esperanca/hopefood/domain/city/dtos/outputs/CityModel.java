package com.esperanca.hopefood.domain.city.dtos.outputs;

import com.esperanca.hopefood.domain.state.dtos.outputs.StateOutput;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CityModel {
	private Long id;
	private String name;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
	private StateOutput state;
}
