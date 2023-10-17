package com.esperanca.hopefood.domain.kitchen.services.crud;

import com.esperanca.hopefood.core.utils.propertycopier.PropertyCopier;
import com.esperanca.hopefood.domain.kitchen.assemblers.KitchenInputDisassembler;
import com.esperanca.hopefood.domain.kitchen.assemblers.KitchenModelAssembler;
import com.esperanca.hopefood.domain.kitchen.dtos.inputs.KitchenInput;
import com.esperanca.hopefood.domain.kitchen.dtos.outputs.KitchenModel;
import com.esperanca.hopefood.domain.kitchen.entities.Kitchen;
import com.esperanca.hopefood.domain.kitchen.exceptions.KitchenInUseException;
import com.esperanca.hopefood.domain.kitchen.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KitchenCrudServiceImpl implements KitchenCrudService {

	@Autowired
	private PropertyCopier copier;

	@Autowired
	private KitchenRepository repository;

	@Autowired
	private KitchenModelAssembler assembler;

	@Autowired
	private KitchenInputDisassembler disassembler;


	@Override
	@Transactional(readOnly = true)
	public List<KitchenModel> findAll() {
		final List<Kitchen> kitchens = repository.findAll();
		return assembler.toCollectionModel(kitchens);
	}

	@Override
	@Transactional(readOnly = true)
	public KitchenModel findById(Long id) {
		return repository.findById(id)
				.map(kitchen -> assembler.toModel(kitchen))
				.orElseGet(KitchenModel::new);
	}

	@Override
	@Transactional
	public KitchenModel save(KitchenInput kitchenInput) {
		Kitchen kitchen = disassembler.toEntity(kitchenInput);
		kitchen = repository.save(kitchen);
		return assembler.toModel(kitchen);
	}

	@Override
	@Transactional
	public KitchenModel update(Long id, KitchenInput kitchenInput) {
		Kitchen kitchen = findKitchenById(id);
		copier.copyProperties(kitchenInput, kitchen);
		kitchen = repository.save(kitchen);

		return assembler.toModel(kitchen);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws KitchenInUseException {
		try {
			repository.deleteById(id);
			repository.flush();
		}
		catch (DataIntegrityViolationException exception) {
			throw new KitchenInUseException(id);
		}
	}

	public Kitchen findKitchenById(Long id) {
		return repository.findById(id)
				.orElseGet(Kitchen::new);
	}
}
