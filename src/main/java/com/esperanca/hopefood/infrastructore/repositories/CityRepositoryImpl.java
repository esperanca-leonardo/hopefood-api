package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.City;
import com.esperanca.hopefood.domain.repositories.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<City> findAll() {
    return this.entityManager.createQuery("from City", City.class)
        .getResultList();
  }

  @Override
  public City findById(Long id) {
    return this.entityManager.find(City.class, id);
  }

  @Override
  @Transactional
  public City save(City city) {
    return this.entityManager.merge(city);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    var city = this.findById(id);
    this.entityManager.remove(city);
  }
}
