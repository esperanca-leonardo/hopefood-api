//package com.esperanca.hopefood.restaurant.services;
//
//import com.esperanca.hopefood.address.embeddables.Address;
//import com.esperanca.hopefood.city.entities.City;
//import com.esperanca.hopefood.kitchen.entities.Kitchen;
//import com.esperanca.hopefood.restaurant.entities.Restaurant;
//import com.esperanca.hopefood.restaurant.exceptions.RestaurantNotFoundException;
//import com.esperanca.hopefood.restaurant.repositories.RestaurantRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.springframework.beans.BeanUtils.copyProperties;
//
//@Service
//@AllArgsConstructor
//public class RestaurantService {
//
//	private CityService cityService;
//
//	private ObjectMapper objectMapper;
//
//	private KitchenService kitchenService;
//
//	private RestaurantRepository restaurantRepository;
//
////	private void merge(Map<String, Object> newValues, Restaurant oldValues)
////			throws IllegalArgumentException, NullPointerException, InaccessibleObjectException {
////
////		Restaurant convertValue = convertToEntity(newValues);
////
////		newValues.forEach((key, value) -> {
////			Field field = findField(Restaurant.class, key);
////			setFieldAccessible(field);
////			Object newConvertedValue = getField(field, convertValue);
////			setField(field, oldValues, newConvertedValue);
////		});
////	}
//
////	private void setFieldAccessible(Field field) throws NullPointerException,
////			InaccessibleObjectException {
////		requireNonNull(field).setAccessible(true);
////	}
//
////	private Restaurant convertToEntity(Map<String, Object> newValues) {
////		return objectMapper.convertValue(newValues, Restaurant.class);
////	}
//
//	private RestaurantSummaryDto convertToOutputDto(Restaurant restaurant) {
//		return modelMapper.map(restaurant, RestaurantSummaryDto.class);
//	}
//
//	public Restaurant toEntity(RestaurantInput restaurantInput) {
//		return modelMapper.map(restaurantInput, Restaurant.class);
//	}
//
//	private Long getCityId(RestaurantInput restaurantInput) {
//		return restaurantInput.getAddress().getCity().getId();
//	}
//
//	private Long getKitchenId(RestaurantInput restaurantInput) {
//		return restaurantInput.getKitchen().getId();
//	}
//
//	@Transactional(readOnly = true)
//	private Restaurant findRestaurantById(Long id) throws RestaurantNotFoundException {
//		return restaurantRepository.findById(id)
//				.orElseThrow(() -> new RestaurantNotFoundException(id));
//	}
//
//	@Transactional(readOnly = true)
//	public List<RestaurantSummaryDto> findAll() {
//		return restaurantRepository.findAll()
//				.stream()
//				.map(this::convertToOutputDto)
//				.toList();
//	}
//
//	@Transactional(readOnly = true)
//	public RestaurantSummaryDto findById(Long id) throws RestaurantNotFoundException {
//		return restaurantRepository.findById(id)
//				.map(this::convertToOutputDto)
//				.orElseThrow(() -> new RestaurantNotFoundException(id));
//	}
//
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public RestaurantSummaryDto save(RestaurantInput restaurantInput) {
//		final Long cityId = getCityId(restaurantInput);
//		final Long kitchenId = getKitchenId(restaurantInput);
//
//		Restaurant restaurant = toEntity(restaurantInput);
//		final Address address = restaurant.getAddress();
//		final City city = cityService.findCityById(cityId);
//		final Kitchen kitchen = kitchenService.findKitchenById(kitchenId);
//
//		address.setCity(city);
//		restaurant.setAddress(address);
//		restaurant.setKitchen(kitchen);
//		restaurant = restaurantRepository.save(restaurant);
//
//		return convertToOutputDto(restaurant);
//	}
//
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public RestaurantSummaryDto update(RestaurantInput restaurantInput,
//																		 Long id) throws RestaurantNotFoundException {
//
//		final Long cityId = getCityId(restaurantInput);
//		final Long kitchenId = getKitchenId(restaurantInput);
//
//		Restaurant restaurant = findRestaurantById(id);
//		final Address address = restaurant.getAddress();
//		final City city = cityService.findCityById(cityId);
//		final Kitchen kitchen = kitchenService.findKitchenById(kitchenId);
//
//		copyProperties(restaurantInput, restaurant,
//				"kitchen", "city"
//		);
//		address.setCity(city);
//		restaurant.setAddress(address);
//		restaurant.setKitchen(kitchen);
//		restaurant = restaurantRepository.save(restaurant);
//
//		return convertToOutputDto(restaurant);
//	}
//
////	public RestaurantCompleteOutputDto partialUpdate(Map<String, Object> newValues, Long id)
////			throws IllegalArgumentException, NullPointerException, BusinessLogicException {
////
////		Restaurant restaurant = fetchRestaurantById(id);
////		Long actualKitchenId = getKitchenId(restaurant);
////		merge(newValues, restaurant);
////		Long possibleNewKitchenId = getKitchenId(restaurant);
////
////		if (!actualKitchenId.equals(possibleNewKitchenId)) {
////			checkAndSetIfTheKitchenExists(restaurant, possibleNewKitchenId);
////		}
////		restaurant = restaurantRepository.save(restaurant);
////
////		return convertToOutputDto(restaurant);
////	}
//
//	@Transactional
//	public void delete(Long id) throws RestaurantNotFoundException {
//		try {
//			restaurantRepository.deleteById(id);
//		}
//		catch (EmptyResultDataAccessException exception) {
//			throw new RestaurantNotFoundException(id);
//		}
//	}
//}