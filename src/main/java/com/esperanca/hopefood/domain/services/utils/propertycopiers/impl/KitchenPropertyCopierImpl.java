package com.esperanca.hopefood.domain.services.utils.propertycopiers.impl;

import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenInputDto;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.utils.propertycopiers.interfaces.KitchenPropertyCopier;
import com.esperanca.hopefood.domain.services.shared.AbstractMapperProvider;
import org.springframework.stereotype.Component;

@Component
public class KitchenPropertyCopierImpl extends AbstractMapperProvider
		implements KitchenPropertyCopier {

	@Override
	public void copyProperties(KitchenInputDto kitchenInputDto, Kitchen kitchen) {
		modelMapper.map(kitchenInputDto, kitchen);
	}
}
