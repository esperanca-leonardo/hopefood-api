package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.Permission;
import com.esperanca.hopefood.domain.repositories.PermissionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Permission> findAll() {
    return this.entityManager
        .createQuery("from Permission", Permission.class)
        .getResultList();
  }

  @Override
  public Permission findById(Long id) {
    return this.entityManager.find(Permission.class, id);
  }

  @Override
  @Transactional
  public Permission save(Permission permission) {
    return this.entityManager.merge(permission);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    var permission = this.findById(id);
    this.entityManager.remove(permission);
  }
}
