package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.Permission;

import java.util.List;

public interface PermissionRepository {

  List<Permission> findAll();

  Permission findById(Long id);

  Permission save(Permission permission);

  void delete(Long id);
}
