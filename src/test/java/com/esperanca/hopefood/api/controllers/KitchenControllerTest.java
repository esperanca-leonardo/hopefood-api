package com.esperanca.hopefood.api.controllers;

import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenControllerTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup() {
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	@DisplayName("Find all should return status 200 and 2 kitchens")
	public void findAllShouldReturnStatus200And2Kitchens() {
		given()
				.accept(JSON)
		.when()
				.get()
		.then()
				.statusCode(OK.value())
				.body("", hasSize(2))
				.body("name", hasItems("Italiana", "Mexicana"));
	}

	@Test
	@DisplayName("Find by id should return status 200 and a kitchen when ID " +
			"is valid")
	public void findByIdShouldReturnStatus200AndAKitchenWhenIdIsValid() throws JSONException {
		final var ID = 1;
		var kitchenReturned = new JSONObject();

		kitchenReturned.put("id", ID);
		kitchenReturned.put("name", "Italiana");
		kitchenReturned.put("description", "Massas, pizzas e molhos " +
				"saborosos. Ingredientes frescos como azeite de oliva, tomates, " +
				"queijos e ervas aromáticas. Tradição e autenticidade em cada " +
				"prato."
		);
		given()
				.pathParam("id", ID)
				.accept(JSON)
		.when()
				.get("/{id}")
		.then()
				.statusCode(OK.value())
				.body("id", equalTo(kitchenReturned.get("id")))
				.body("name", equalTo(kitchenReturned.get("name")))
				.body("description", equalTo(kitchenReturned.get("description")));
	}

	@Test
	@DisplayName("Find by id should return status 404 and a " +
			"KitchenNotFoundError when ID is invalid")
	public void findByIdShouldReturnStatus404AndAKitchenNotFoundErrorWhenIdIsInvalid()
			throws JSONException {

		final var ID = 10;
		final var STATUS = HttpStatus.NOT_FOUND.value();

		var errorResponse = new JSONObject();
		errorResponse.put("status", STATUS);
		errorResponse.put("type", "https://hopefood.com.br/kitchen-not-found");
		errorResponse.put("title", "Kitchen not found");
		errorResponse.put("detail", "Kitchen not found with ID: " + ID);

		given()
				.pathParam("id", ID)
				.accept(JSON)
		.when()
				.get("/{id}")
		.then()
				.statusCode(STATUS)
				.body("status", equalTo(errorResponse.get("status")))
				.body("type", equalTo(errorResponse.get("type")))
				.body("title", equalTo(errorResponse.get("title")))
				.body("detail", equalTo(errorResponse.get("detail")));
	}

	@Test
	@DisplayName("Save should return status 201 and the kitchen saved")
	public void saveShouldReturnStatus201AndTheKitchenSaved() throws JSONException {
		var kitchen = new JSONObject();
		kitchen.put("name", "NameTest");
		kitchen.put("description", "DescriptionTest");

		given()
				.contentType(JSON)
				.accept(JSON)
				.body(kitchen.toString())
		.when()
				.post()
		.then()
				.statusCode(CREATED.value())
				.body("name", equalTo(kitchen.get("name")))
				.body("description", equalTo(kitchen.get("description")));
	}

//	@Test
//	@DisplayName("Update should return status 200 and the kitchen updated")
//	public void updateShouldReturnStatus200AndTheKitchenUpdated() {
//
//	}
}

