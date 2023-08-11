package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.inputs.city.CityInputDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CityCompleteDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CitySummaryDto;
import com.esperanca.hopefood.domain.exceptions.city.CityInUseException;
import com.esperanca.hopefood.domain.exceptions.city.CityNotFoundException;
import com.esperanca.hopefood.domain.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService service;

	@GetMapping
	@ResponseStatus(OK)
	public List<CitySummaryDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public CityCompleteDto findById(@PathVariable Long id) throws CityNotFoundException {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public CityCompleteDto save(@RequestBody @Valid CityInputDto cityInputDto) {
		return service.save(cityInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public CityCompleteDto update(@RequestBody @Valid CityInputDto
			cityInputDto, @PathVariable Long id) throws CityNotFoundException {
		return service.update(cityInputDto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws
			CityNotFoundException, CityInUseException {
		service.delete(id);
	}
}
