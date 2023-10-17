package com.esperanca.hopefood.domain.kitchen.assemblers;

import com.esperanca.hopefood.core.assemblers.Disassembler;
import com.esperanca.hopefood.core.beans.modelmapper.ModelMapperProvider;
import com.esperanca.hopefood.domain.kitchen.dtos.inputs.KitchenInput;
import com.esperanca.hopefood.domain.kitchen.entities.Kitchen;
import org.springframework.stereotype.Component;

@Component
public class KitchenInputDisassembler extends ModelMapperProvider
		implements Disassembler<KitchenInput, Kitchen> {

	@Override
	public Kitchen toEntity(KitchenInput kitchenInput) {
		return modelMapper.map(kitchenInput, Kitchen.class);
	}
}
