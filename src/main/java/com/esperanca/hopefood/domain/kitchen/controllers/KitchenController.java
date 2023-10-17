package com.esperanca.hopefood.domain.kitchen.controllers;

import com.esperanca.hopefood.domain.kitchen.dtos.outputs.KitchenModel;
import com.esperanca.hopefood.domain.kitchen.exceptions.KitchenInUseException;
import com.esperanca.hopefood.domain.kitchen.dtos.inputs.KitchenInput;
import com.esperanca.hopefood.domain.kitchen.services.crud.KitchenCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenCrudService service;

	@GetMapping
	public List<KitchenModel> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public KitchenModel findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public KitchenModel save(@RequestBody @Valid KitchenInput kitchenInput) {
		return service.save(kitchenInput);
	}

	@PutMapping("/{id}")
	public KitchenModel update(@RequestBody @Valid
			KitchenInput kitchenInput, @PathVariable Long id) {
		return service.update(id, kitchenInput);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws KitchenInUseException {
		service.deleteById(id);
	}
}
