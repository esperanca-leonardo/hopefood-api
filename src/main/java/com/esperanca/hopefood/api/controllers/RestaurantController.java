package com.esperanca.hopefood.api.controllers;

import com.esperanca.hopefood.core.dtos.restaurant.RestaurantInputDto;
import com.esperanca.hopefood.core.dtos.restaurant.RestaurantOutputDto;
import com.esperanca.hopefood.domain.exceptions.BusinessLogicException;
import com.esperanca.hopefood.domain.exceptions.restaurant.RestaurantNotFoundException;
import com.esperanca.hopefood.domain.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

	private RestaurantService restaurantService;

	@GetMapping
	@ResponseStatus(OK)
	public List<RestaurantOutputDto> findAll() {
		return restaurantService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public RestaurantOutputDto findById(@PathVariable Long id) throws RestaurantNotFoundException {
		return restaurantService.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public RestaurantOutputDto save(@RequestBody @Valid RestaurantInputDto restaurantInputDto)
			throws BusinessLogicException {
		return restaurantService.save(restaurantInputDto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	public RestaurantOutputDto update(@RequestBody @Valid RestaurantInputDto restaurantInputDto,
			@PathVariable Long id) throws BusinessLogicException {
		return restaurantService.update(restaurantInputDto, id);
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
		restaurantService.delete(id);
	}
}
