package com.esperanca.hopefood.domain.services.utils.shared;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapperProvider {

	@Autowired
	protected ModelMapper modelMapper;
}
