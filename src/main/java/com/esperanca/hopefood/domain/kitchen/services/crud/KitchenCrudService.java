package com.esperanca.hopefood.domain.kitchen.services.crud;

import com.esperanca.hopefood.core.interfaces.crud.Crud;
import com.esperanca.hopefood.domain.kitchen.dtos.inputs.KitchenInput;
import com.esperanca.hopefood.domain.kitchen.dtos.outputs.KitchenModel;
import com.esperanca.hopefood.domain.kitchen.exceptions.KitchenInUseException;

import java.util.List;

public interface KitchenCrudService
		extends Crud<Long, KitchenInput, KitchenModel> {

	@Override
	List<KitchenModel> findAll();

	@Override
	KitchenModel findById(Long id);

	@Override
	KitchenModel save(KitchenInput kitchenInput);

	@Override
	KitchenModel update(Long id, KitchenInput kitchenInput);

	@Override
	void deleteById(Long id) throws KitchenInUseException;
}
