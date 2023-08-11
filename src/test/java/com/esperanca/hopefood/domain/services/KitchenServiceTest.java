//package com.esperanca.hopefood.domain.services;
//
//import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
//import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
//import com.esperanca.hopefood.domain.models.Kitchen;
//import com.esperanca.hopefood.domain.repositories.KitchenRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class KitchenServiceTest {
//
//	@Autowired
//	private KitchenService kitchenService;
//
//	@MockBean
//	private KitchenRepository kitchenRepository;
//
//	@Test
//	@DisplayName("FindAll should return a list of kitchens")
//	public void findAllShouldReturnAListOfKitchens() {
//		final List<Kitchen> KITCHENS = List.of(
//				mock(Kitchen.class),
//				mock(Kitchen.class),
//				mock(Kitchen.class)
//		);
//		when(this.kitchenRepository.findAll()).thenReturn(KITCHENS);
//
//		final List<Kitchen> result = this.kitchenService.findAll().s;
//
//		assertEquals(KITCHENS, result);
//		assertEquals(3, result.size());
//		verify(this.kitchenRepository, times(1))
//				.findAll();
//	}
//
//	@Test
//	@DisplayName("FindById should return a kitchen when id exist")
//	public void findByIdShouldReturnAKitchenWhenIdExist() {
//		final var ID = 1L;
//		final var KITCHEN = mock(Kitchen.class);
//
//		when(this.kitchenRepository.findById(anyLong()))
//				.thenReturn(Optional.of(KITCHEN));
//
//		final var RESULT = this.kitchenService.findById(ID);
//
//		assertEquals(KITCHEN, RESULT);
//		verify(this.kitchenRepository, times(1))
//				.findById(anyLong());
//	}
//
//	@Test
//	@DisplayName("FindById should throw a KitchenNotFoundException when id " +
//			"not exist")
//	public void findByIdShouldThrowKitchenNotFoundExceptionWhenIdNotExist() {
//		final var ID = 1L;
//		final var EXPECTED_MESSAGE = "Kitchen not found with ID: " + ID;
//
//		when(this.kitchenRepository.findById(anyLong()))
//				.thenThrow(new KitchenNotFoundException(ID));
//
//		final var EXCEPTION = assertThrows(KitchenNotFoundException.class,
//				() -> this.kitchenService.findById(ID)
//		);
//		assertEquals(EXPECTED_MESSAGE, EXCEPTION.getMessage());
//		verify(this.kitchenRepository, times(1))
//				.findById(anyLong());
//	}
//
//	@Test
//	@DisplayName("Save should return a kitchen")
//	public void saveShouldReturnAKitchen() {
//		final var KITCHEN = new Kitchen();
//
//		when(this.kitchenRepository.save(eq(KITCHEN))).thenReturn(KITCHEN);
//
//		final var RESULT = this.kitchenService.save(KITCHEN);
//
//		assertEquals(KITCHEN, RESULT);
//		verify(this.kitchenRepository, times(1))
//				.save(eq(KITCHEN));
//	}
//
//	@Test
//	@DisplayName("Update should return an updated kitchen when id exist")
//	public void updateShouldUpdateAKitchenWhenIdExist() {
//		final var ID = 1L;
//		final var KITCHEN = mock(Kitchen.class);
//		final var UPDATED_KITCHEN = mock(Kitchen.class);
//
//		when(this.kitchenRepository.findById(anyLong()))
//				.thenReturn(Optional.of(KITCHEN));
//		when(this.kitchenRepository.save(eq(KITCHEN)))
//				.thenReturn(UPDATED_KITCHEN);
//
//		final var RESULT = this.kitchenService.update(KITCHEN, ID);
//
//		assertEquals(UPDATED_KITCHEN, RESULT);
//		verify(this.kitchenRepository, times(1))
//				.findById(anyLong());
//		verify(this.kitchenRepository, times(1))
//				.save(eq(KITCHEN));
//	}
//
//	@Test
//	@DisplayName("Delete should delete a kitchen when the id exist")
//	public void deleteShouldDeleteAKitchenWhenIdExist() {
//		final var ID = 1L;
//
//		doNothing().when(this.kitchenRepository).deleteById(anyLong());
//
//		this.kitchenService.delete(ID);
//
//		verify(this.kitchenRepository, times(1))
//				.deleteById(ID);
//		assertDoesNotThrow(() -> this.kitchenService.delete(ID));
//	}
//
//	@Test
//	@DisplayName("Delete should throw a KitchenNotFoundException when the id " +
//			"not exist")
//	public void deleteShouldThrowKitchenNotFoundExceptionWhenIdNotExist() {
//		final var ID = 1L;
//		final var EXPECTED_MESSAGE = "Kitchen not found with ID: " + ID;
//
//		doThrow(EmptyResultDataAccessException.class)
//				.when(this.kitchenRepository)
//				.deleteById(anyLong());
//
//		final var EXCEPTION = assertThrows(KitchenNotFoundException.class,
//				() -> this.kitchenService.delete(ID)
//		);
//		assertEquals(EXPECTED_MESSAGE, EXCEPTION.getMessage());
//	}
//
//	@Test
//	@DisplayName("Delete should throw a KitchenInUseException when id is in " +
//			"use by another class")
//	public void deleteShouldThrowKitchenInUseExceptionWhenIdInUse() {
//		final var ID = 1L;
//		final var EXPECTED_MESSAGE = "Kitchen in use with ID: " + ID;
//
//		doThrow(DataIntegrityViolationException.class)
//				.when(this.kitchenRepository)
//				.deleteById(anyLong());
//
//		final var EXCEPTION = assertThrows(KitchenInUseException.class,
//				() -> this.kitchenService.delete(ID)
//		);
//		assertEquals(EXPECTED_MESSAGE, EXCEPTION.getMessage());
//	}
//}