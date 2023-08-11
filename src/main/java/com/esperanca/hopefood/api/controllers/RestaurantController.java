package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.inputs.restaurant.RestaurantInputDto;
import com.esperanca.hopefood.core.dtos.outputs.restaurant.RestaurantSummaryDto;
import com.esperanca.hopefood.domain.exceptions.restaurant.RestaurantNotFoundException;
import com.esperanca.hopefood.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService service;

	@GetMapping
	@ResponseStatus(OK)
	public List<RestaurantSummaryDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public RestaurantSummaryDto findById(@PathVariable Long id)
			throws RestaurantNotFoundException {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public RestaurantSummaryDto save(@RequestBody
			@Valid RestaurantInputDto restaurantInputDto) {
		return service.save(restaurantInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public RestaurantSummaryDto update(@RequestBody @Valid RestaurantInputDto
			restaurantInputDto, @PathVariable Long id) throws RestaurantNotFoundException {
		return service.update(restaurantInputDto, id);
	}

//	@PatchMapping("/{id}")
//	public RestaurantOutputDto partialUpdate(@RequestBody Map<String, Object> mapRestaurant,
//			@PathVariable Long id) throws IllegalArgumentException, NullPointerException,
//			BusinessLogicException {
//		return restaurantService.partialUpdate(mapRestaurant, id);
//	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) throws RestaurantNotFoundException {
		service.delete(id);
	}
}
