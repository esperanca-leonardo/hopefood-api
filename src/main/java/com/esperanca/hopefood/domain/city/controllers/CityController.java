package com.esperanca.hopefood.domain.city.controllers;

import com.esperanca.hopefood.domain.city.dtos.inputs.CityInput;
import com.esperanca.hopefood.domain.city.dtos.outputs.CityModel;
import com.esperanca.hopefood.domain.city.exceptions.CityInUseException;
import com.esperanca.hopefood.domain.city.services.crud.CityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityCrudService service;

	@GetMapping
	public List<CityModel> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public CityModel findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public CityModel save(@RequestBody @Valid CityInput cityInput) {
		return service.save(cityInput);
	}

	@PutMapping("/{id}")
	public CityModel update(@RequestBody @Valid
			CityInput cityInput, @PathVariable Long id) {
		return service.update(id, cityInput);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws CityInUseException {
		service.deleteById(id);
	}
}
