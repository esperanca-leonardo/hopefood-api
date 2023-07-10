package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.Kitchen;
import com.esperanca.hopefood.domain.repositories.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Kitchen> findAll() {
    return this.entityManager.createQuery("from Kitchen", Kitchen.class)
        .getResultList();
  }

  @Override
  public Kitchen findById(Long id) {
    return this.entityManager.find(Kitchen.class, id);
  }

  @Override
  @Transactional
  public Kitchen save(Kitchen kitchen) {
    return this.entityManager.merge(kitchen);
  }

  @Override
  @Transactional
  public void remove(Long id) {
    var kitchen = this.findById(id);
    this.entityManager.remove(kitchen);
  }
}
