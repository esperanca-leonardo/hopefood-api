package com.esperanca.hopefood.domain.kitchen.assemblers;

import com.esperanca.hopefood.core.assemblers.Assembler;
import com.esperanca.hopefood.core.beans.modelmapper.ModelMapperProvider;
import com.esperanca.hopefood.domain.kitchen.dtos.outputs.KitchenModel;
import com.esperanca.hopefood.domain.kitchen.entities.Kitchen;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KitchenModelAssembler extends ModelMapperProvider
		implements Assembler<Kitchen, KitchenModel> {

	@Override
	public KitchenModel toModel(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenModel.class);
	}

	@Override
	public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {
		return kitchens.stream()
				.map(this::toModel)
				.toList();
	}
}
