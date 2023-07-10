package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.State;
import com.esperanca.hopefood.domain.repositories.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<State> findAll() {
    return this.entityManager.createQuery("from State", State.class)
        .getResultList();
  }

  @Override
  public State findById(Long id) {
    return this.entityManager.find(State.class, id);
  }

  @Override
  @Transactional
  public State save(State state) {
    return this.entityManager.merge(state);
  }

  @Override
  @Transactional
  public void remove(Long id) {
    var state = this.findById(id);
    this.entityManager.remove(state);
  }
}
