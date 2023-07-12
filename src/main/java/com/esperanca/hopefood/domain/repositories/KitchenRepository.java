package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.Kitchen;

import java.util.List;

public interface KitchenRepository {

  List<Kitchen> findAll();

  Kitchen findById(Long id);

  Kitchen save(Kitchen kitchen);

  void delete(Long id);
}
