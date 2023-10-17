package com.esperanca.hopefood.core.beans.modelmapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ModelMapperProvider {

	@Autowired
	protected ModelMapper modelMapper;
}
