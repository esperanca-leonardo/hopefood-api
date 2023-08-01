package com.esperanca.hopefood.api.dtos.city;

import com.esperanca.hopefood.api.dtos.state.StateOutputDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityOutputDto {
	private Long id;
	private String name;
	private StateOutputDto state;
}
