package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
		var kitchen = this.kitchenService.findById(id);

		if (Objects.nonNull(kitchen)) {
			return ResponseEntity.ok(kitchen);
		}
		return ResponseEntity.notFound().build();
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

		if (Objects.nonNull(kitchenFromDb)) {
			BeanUtils.copyProperties(kitchen, kitchenFromDb, "id");
			this.kitchenService.save(kitchenFromDb);

			return ResponseEntity.ok(kitchenFromDb);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
		var kitchen = this.kitchenService.findById(id);
		var httpStatus = HttpStatus.NO_CONTENT;

		try {
			if (Objects.nonNull(kitchen)) {
				this.kitchenService.delete(id);
			}
			else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
		}
		catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			httpStatus = HttpStatus.CONFLICT;
		}
		return ResponseEntity.status(httpStatus).build();
	}
}
