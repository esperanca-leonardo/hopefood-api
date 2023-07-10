package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.City;

import java.util.List;

public interface CityRepository {

  List<City> findAll();

  City findById(Long id);

  City save(City city);

  void remove(Long id);
}
