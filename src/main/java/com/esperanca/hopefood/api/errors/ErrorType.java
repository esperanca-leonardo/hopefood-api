package com.esperanca.hopefood.api.errors;

import lombok.Getter;

@Getter
public enum ErrorType {
	BUSINESS_LOGIC("/business-logic", "Business Logic"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
	KITCHEN_IN_USE("/kitchen-in-use", "Kitchen in use"),
	KITCHEN_NOT_FOUND("/kitchen-not-found", "Kitchen not found"),
	RESTAURANT_NOT_FOUND("/restaurant-not-found", "Restaurant not found"),
	INVALID_DATA("/invalid-data", "Invalid data");

	private final String title;
	private final String path;

	ErrorType(String path, String title) {
		this.path = "https://hopefood.com.br" + path;
		this.title = title;
	}
}
