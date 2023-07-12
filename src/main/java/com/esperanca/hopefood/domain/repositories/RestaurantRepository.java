package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.Restaurant;

import java.util.List;

public interface RestaurantRepository {

  List<Restaurant> findAll();

  Restaurant findById(Long id);

  Restaurant save(Restaurant restaurant);

  void delete(Long id);
}
