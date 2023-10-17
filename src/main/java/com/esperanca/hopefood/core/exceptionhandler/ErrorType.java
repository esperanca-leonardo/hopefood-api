package com.esperanca.hopefood.core.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorType {
	INVALID_DATA("/invalid-data", "Invalid data"),
	BUSINESS_LOGIC("/business-logic", "Business Logic"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
	KITCHEN_IN_USE("/kitchen-in-use", "Kitchen in use"),
	KITCHEN_NOT_FOUND("/kitchen-not-found", "Kitchen not found"),
	RESTAURANT_NOT_FOUND("/restaurant-not-found", "Restaurant not found"),
	STATE_IN_USE("/state-in-use", "State in use"),
	STATE_NOT_FOUND("/state-not-found", "State not found"),
	CITY_NOT_FOUND("/city-not-found", "City not found"),
	CITY_IN_USE("/city-in-use", "City in use");

	private final String title;
	private final String path;

	ErrorType(String path, String title) {
		this.path = "https://hopefood.com.br" + path;
		this.title = title;
	}
}
