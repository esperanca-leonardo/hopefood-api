package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;
import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	private KitchenService kitchenService;

	public KitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Kitchen> findAll() {
		return this.kitchenService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
		var kitchen = this.kitchenService.findById(id);
		var httpStatus = HttpStatus.OK;

		if (Objects.isNull(kitchen)) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return ResponseEntity.status(httpStatus).body(kitchen);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return this.kitchenService.save(kitchen);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@RequestBody Kitchen kitchen,
			@PathVariable Long id) {

		var kitchenFromDb = this.kitchenService.findById(id);
		var httpStatus = HttpStatus.OK;

		if (Objects.nonNull(kitchenFromDb)) {
			BeanUtils.copyProperties(kitchen, kitchenFromDb, "id");
			this.kitchenService.save(kitchenFromDb);
		}
		else {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return ResponseEntity.status(httpStatus).body(kitchenFromDb);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var httpStatus = HttpStatus.NO_CONTENT;
		var message = "";

		try {
			this.kitchenService.delete(id);
		}
		catch (EntityNotFoundException exception) {
			message = exception.getMessage();
			httpStatus = HttpStatus.NOT_FOUND;
		}
		catch (EntityInUseException exception) {
			message = exception.getMessage();
			httpStatus = HttpStatus.CONFLICT;
		}
		return ResponseEntity.status(httpStatus).body(message);
	}
}
