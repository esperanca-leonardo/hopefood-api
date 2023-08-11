package com.esperanca.hopefood.domain.services.utils.converters.interfaces.kitchen;

import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenInputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenSummaryDto;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.utils.converters.interfaces.generics.Converter;

public interface KitchenConverter extends Converter<Kitchen, KitchenInputDto,
		KitchenSummaryDto, KitchenCompleteDto> {
}
