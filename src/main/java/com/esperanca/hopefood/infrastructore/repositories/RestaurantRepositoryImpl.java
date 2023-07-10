package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.Restaurant;
import com.esperanca.hopefood.domain.repositories.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Restaurant> findAll() {
    return this.entityManager
        .createQuery("from Restaurant", Restaurant.class)
        .getResultList();
  }

  @Override
  public Restaurant findById(Long id) {
    return this.entityManager.find(Restaurant.class, id);
  }

  @Override
  @Transactional
  public Restaurant save(Restaurant restaurant) {
    return this.entityManager.merge(restaurant);
  }

  @Override
  @Transactional
  public void remove(Long id) {
    var restaurant = this.findById(id);
    this.entityManager.remove(restaurant);
  }
}
