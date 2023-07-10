package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.State;

import java.util.List;

public interface StateRepository {

  List<State> findAll();

  State findById(Long id);

  State save(State state);

  void remove(Long id);
}
