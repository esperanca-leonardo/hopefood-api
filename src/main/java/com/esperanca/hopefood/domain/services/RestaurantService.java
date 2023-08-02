package com.esperanca.hopefood.domain.services;

import com.esperanca.hopefood.core.dtos.restaurant.RestaurantInputDto;
import com.esperanca.hopefood.core.dtos.restaurant.RestaurantOutputDto;
import com.esperanca.hopefood.domain.exceptions.BusinessLogicException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.exceptions.restaurant.RestaurantNotFoundException;
import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.models.Restaurant;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import com.esperanca.hopefood.domain.repositories.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class RestaurantService {

	private ModelMapper modelMapper;

	private ObjectMapper objectMapper;

	private KitchenRepository kitchenRepository;

	private RestaurantRepository restaurantRepository;

	private void checkAndSetIfTheKitchenExists(Restaurant restaurant, Long kitchenId)
			throws BusinessLogicException {
		try {
			final Kitchen kitchen = kitchenRepository.findById(kitchenId)
					.orElseThrow(() -> new KitchenNotFoundException(kitchenId));

			restaurant.setKitchen(kitchen);
		}
		catch (KitchenNotFoundException exception) {
			throw new BusinessLogicException(exception.getMessage());
		}
	}

//	private void merge(Map<String, Object> newValues, Restaurant oldValues)
//			throws IllegalArgumentException, NullPointerException, InaccessibleObjectException {
//		Restaurant convertValue = objectMapper.convertValue(newValues,
//				Restaurant.class
//		);
//		newValues.forEach((key, value) -> {
//			Field field = findField(Restaurant.class, key);
//			requireNonNull(field).setAccessible(true);
//			Object newConvertedValue = getField(field, convertValue);
//			setField(field, oldValues, newConvertedValue);
//		});
//	}

	private RestaurantOutputDto convertToOutputDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantOutputDto.class);
	}

	public List<RestaurantOutputDto> findAll() {
		return restaurantRepository.findAll()
				.stream()
				.map(this::convertToOutputDto)
				.toList();
	}

	public RestaurantOutputDto findById(Long id) throws RestaurantNotFoundException {
		return restaurantRepository.findById(id)
				.map(this::convertToOutputDto)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
	}

	public RestaurantOutputDto save(RestaurantInputDto restaurantInputDto)
			throws BusinessLogicException {
		final Long KITCHEN_ID = restaurantInputDto.getKitchen().getId();

		Restaurant restaurant = modelMapper.map(restaurantInputDto, Restaurant.class);
		checkAndSetIfTheKitchenExists(restaurant, KITCHEN_ID);
		restaurant = restaurantRepository.save(restaurant);

		return convertToOutputDto(restaurant);
	}

	public RestaurantOutputDto update(RestaurantInputDto restaurantInputDto, Long id)
			throws BusinessLogicException, BeansException {
		final Long KITCHEN_ID = restaurantInputDto.getKitchen().getId();

		Restaurant restaurant = restaurantRepository.findById(id)
						.orElseThrow(() -> new RestaurantNotFoundException(id));

		checkAndSetIfTheKitchenExists(restaurant, KITCHEN_ID);
		copyProperties(restaurantInputDto, restaurant, "kitchen");
		restaurant = restaurantRepository.save(restaurant);

		return convertToOutputDto(restaurant);
	}

//	public RestaurantOutputDto partialUpdate(Map<String, Object> newValues, Long id)
//			throws IllegalArgumentException, NullPointerException,
//			BusinessLogicException {
//
//		var restaurant = restaurantRepository.findById(id)
//				.orElseThrow(() -> new RestaurantNotFoundException(id));
//
//		Long actualKitchenId = restaurant.getKitchen().getId();
//		merge(newValues, restaurant);
//		Long possibleNewKitchenId = restaurant.getKitchen().getId();
//
//		if (!actualKitchenId.equals(possibleNewKitchenId)) {
//			checkAndSetIfTheKitchenExists(restaurant, possibleNewKitchenId);
//		}
//		restaurant = restaurantRepository.save(restaurant);
//
//		return convertToOutputDto(restaurant);
//	}

	public void delete(Long id) throws RestaurantNotFoundException {
		try {
			restaurantRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException exception) {
			throw new RestaurantNotFoundException(id);
		}
	}
}
